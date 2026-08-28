document.addEventListener('DOMContentLoaded', () => {
    // DOM元素获取
    const userList = document.getElementById('user-list');
    const searchBtn = document.getElementById('search-btn');
    const usernameSearch = document.getElementById('username-search');
    const phoneSearch = document.getElementById('phone-search');
    const statusSearch = document.getElementById('status-search');
    const logoutBtn = document.getElementById('logout-btn');
    const modal = document.getElementById('user-modal');
    const modalTitle = document.getElementById('modal-title');
    const userForm = document.getElementById('user-form');
    const addUserBtn = document.getElementById('add-user-btn');
    const closeBtn = document.querySelector('.close-btn');
    const userIdInput = document.getElementById('user-id');
    const usernameInput = document.getElementById('username');
    const phoneInput = document.getElementById('phone');
    const avatarInput = document.getElementById('avatar');
    const statusInput = document.getElementById('status');
    const roleIdInput = document.getElementById('roleId');

    // 后端接口基础地址（按需修改）
    const apiBaseUrl = 'http://localhost:8080';

    // ========== 核心：通用响应处理（含501未认证跳转） ==========
    async function handleApiResponse(response) {
        // 1. 检测501未认证状态，直接跳登录页
        if (response.status === 501 || (await response.clone().json()).code === 501) {
            window.location.href = 'login.html';
            throw new Error('未登录，跳转至登录页');
        }

        // 2. 解析响应数据
        let result;
        try {
            result = await response.json();
        } catch (e) {
            throw new Error('服务器返回数据格式错误');
        }

        // 3. 业务状态码校验
        if (result.code !== 200) {
            throw new Error(result.message || '操作失败');
        }
        return result.data;
    }

    // ========== 加载用户列表 ==========
    async function fetchUsers(params = {}) {
        const query = new URLSearchParams(params).toString();
        const url = `${apiBaseUrl}/user/list?${query}`;

        try {
            const response = await fetch(url);
            const users = await handleApiResponse(response);
            renderUsers(users);
        } catch (error) {
            if (error.message !== '未登录，跳转至登录页') {
                console.error('加载用户失败:', error);
                userList.innerHTML = `<tr><td colspan="9" style="text-align:center; color:red;">${error.message}</td></tr>`;
            }
        }
    }

    // ========== 渲染用户列表 ==========
    function renderUsers(users) {
        userList.innerHTML = '';
        if (!users || users.length === 0) {
            userList.innerHTML = '<tr><td colspan="9" style="text-align:center;">暂无用户数据</td></tr>';
            return;
        }

        users.forEach(user => {
            const row = document.createElement('tr');
            // 头像处理：空值/加载失败不显示
            const avatarHtml = user.avatar ? 
                `<img src="${user.avatar}" alt="头像" style="width:50px;height:50px;border-radius:50%;" onerror="this.style.display='none'">` : '';
            // 时间格式化
            const formatTime = (time) => time ? new Date(time).toLocaleString() : '';

            row.innerHTML = `
                <td>${user.id || ''}</td>
                <td>${user.username || ''}</td>
                <td>${user.phone || ''}</td>
                <td>${avatarHtml}</td>
                <td>${user.status === 1 ? '正常' : user.status === 0 ? '禁用' : ''}</td>
                <td>${user.roleId || ''}</td>
                <td>${formatTime(user.createTime)}</td>
                <td>${formatTime(user.updateTime)}</td>
                <td>
                    <button class="edit-btn" data-id="${user.id}">编辑</button>
                    <button class="delete-btn" data-id="${user.id}">删除</button>
                </td>
            `;
            userList.appendChild(row);
        });
    }

    // ========== 退出登录功能 ==========
    logoutBtn.addEventListener('click', async () => {
        if (!confirm('确定要退出登录吗？')) return;

        try {
            const response = await fetch(`${apiBaseUrl}/auth/logout`, {
                method: 'GET',
                headers: { 'Content-Type': 'application/json' }
            });
            const result = await response.json();
            
            if (result.code === 200) {
                alert('退出登录成功');
                window.location.href = 'login.html';
            } else {
                alert(`退出失败：${result.message}`);
            }
        } catch (error) {
            console.error('退出登录请求失败:', error);
            alert('退出登录失败：服务器/网络异常');
        }
    });

    // ========== 搜索功能 ==========
    searchBtn.addEventListener('click', () => {
        const params = {
            username: usernameSearch.value.trim(),
            phone: phoneSearch.value.trim(),
            status: statusSearch.value
        };
        // 移除空参数
        Object.keys(params).forEach(key => params[key] === '' && delete params[key]);
        fetchUsers(params);
    });

    // ========== 模态框操作 ==========
    function openModal(title, user = null) {
        modalTitle.textContent = title;
        userForm.reset();
        userIdInput.value = '';

        if (user) {
            userIdInput.value = user.id;
            usernameInput.value = user.username;
            phoneInput.value = user.phone;
            avatarInput.value = user.avatar || '';
            statusInput.value = user.status ?? 1;
            roleIdInput.value = user.roleId;
        }
        modal.style.display = 'block';
    }

    function closeModal() {
        modal.style.display = 'none';
    }

    addUserBtn.addEventListener('click', () => openModal('添加用户'));
    closeBtn.addEventListener('click', closeModal);
    window.addEventListener('click', (e) => e.target === modal && closeModal());

    // ========== 保存用户（新增/编辑） ==========
    userForm.addEventListener('submit', async (e) => {
        e.preventDefault();
        const id = userIdInput.value;
        const userData = {
            username: usernameInput.value.trim(),
            phone: phoneInput.value.trim(),
            avatar: avatarInput.value.trim(),
            status: parseInt(statusInput.value),
            roleId: parseInt(roleIdInput.value)
        };

        // 基础校验
        if (!userData.username) return alert('请输入用户名');
        if (!userData.phone) return alert('请输入手机号');
        if (isNaN(userData.roleId) || userData.roleId <= 0) return alert('请输入有效角色ID');

        const url = id ? `${apiBaseUrl}/user/${id}` : `${apiBaseUrl}/user`;
        const method = id ? 'PUT' : 'POST';

        try {
            const response = await fetch(url, {
                method,
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(userData)
            });
            await handleApiResponse(response);
            alert(id ? '用户更新成功' : '用户添加成功');
            closeModal();
            fetchUsers();
        } catch (error) {
            if (error.message !== '未登录，跳转至登录页') {
                alert(`保存失败：${error.message}`);
            }
        }
    });

    // ========== 编辑/删除用户 ==========
    userList.addEventListener('click', async (e) => {
        const target = e.target;
        const id = target.dataset.id;
        if (!id) return;

        // 编辑用户
        if (target.classList.contains('edit-btn')) {
            try {
                const response = await fetch(`${apiBaseUrl}/user/${id}`);
                const user = await handleApiResponse(response);
                openModal('编辑用户', user);
            } catch (error) {
                if (error.message !== '未登录，跳转至登录页') {
                    alert(`加载用户失败：${error.message}`);
                }
            }
        }

        // 删除用户
        if (target.classList.contains('delete-btn')) {
            if (!confirm(`确定删除ID为${id}的用户吗？`)) return;
            try {
                const response = await fetch(`${apiBaseUrl}/user/${id}`, { method: 'DELETE' });
                await handleApiResponse(response);
                alert(`用户${id}删除成功`);
                fetchUsers();
            } catch (error) {
                if (error.message !== '未登录，跳转至登录页') {
                    alert(`删除失败：${error.message}`);
                }
            }
        }
    });

    // 页面加载时默认加载所有用户
    fetchUsers();
});