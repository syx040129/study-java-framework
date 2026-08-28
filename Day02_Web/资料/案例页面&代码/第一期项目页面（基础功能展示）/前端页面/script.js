document.addEventListener('DOMContentLoaded', () => {
    const userList = document.getElementById('user-list');
    const searchBtn = document.getElementById('search-btn');
    const usernameSearch = document.getElementById('username-search');
    const phoneSearch = document.getElementById('phone-search');
    const statusSearch = document.getElementById('status-search');

    const modal = document.getElementById('user-modal');
    const modalTitle = document.getElementById('modal-title');
    const userForm = document.getElementById('user-form');
    const addUserBtn = document.getElementById('add-user-btn');
    const closeBtn = document.querySelector('.close-btn');
    const saveUserBtn = document.getElementById('save-user-btn');
    
    const userIdInput = document.getElementById('user-id');
    const usernameInput = document.getElementById('username');
    const phoneInput = document.getElementById('phone');
    const avatarInput = document.getElementById('avatar');
    const statusInput = document.getElementById('status');
    const roleIdInput = document.getElementById('roleId');

    const apiBaseUrl = 'http://localhost:8080'; // 请根据您的后端服务地址修改

    // 通用响应处理函数：解析Result格式，判断是否成功
    async function handleResponse(response) {
        const result = await response.json();

        // 返回核心业务数据
        return result;
    }

    // 1. 获取并展示用户列表
    async function fetchUsers(params = {}) {
        const query = new URLSearchParams(params).toString();
        const url = `${apiBaseUrl}/user/list?${query}`;

        try {
            const response = await fetch(url);
            if (!response.ok) {
                throw new Error(`网络请求失败！状态码：${response.status}`);
            }
            // 解析Result格式，获取data中的用户列表
            const users = await handleResponse(response);
            renderUsers(users);
        } catch (error) {
            console.error('获取用户列表失败:', error);
            userList.innerHTML = `<tr><td colspan="9" style="text-align:center;color:red;">${error.message || '加载用户数据失败'}</td></tr>`;
        }
    }

    // 渲染用户列表到表格
    function renderUsers(users) {
        userList.innerHTML = '';
        if (!users || users.length === 0) {
            userList.innerHTML = '<tr><td colspan="9" style="text-align:center;">暂无数据</td></tr>';
            return;
        }

        users.forEach(user => {
            const row = document.createElement('tr');
            row.innerHTML = `
                <td>${user.id}</td>
                <td>${user.username}</td>
                <td>${user.phone}</td>
                <td><img src="${user.avatar || ''}" alt="头像" style="width: 50px; height: 50px; border-radius: 50%;"></td>
                <td>${user.status === 1 ? '正常' : '禁用'}</td>
                <td>${user.roleId}</td>
                <td>${new Date(user.createTime).toLocaleString()}</td>
                <td>${new Date(user.updateTime).toLocaleString()}</td>
                <td class="action-buttons">
                    <button class="edit-btn" data-id="${user.id}">编辑</button>
                    <button class="delete-btn" data-id="${user.id}">删除</button>
                </td>
            `;
            userList.appendChild(row);
        });
    }

    // 页面加载时获取所有用户
    fetchUsers();

    // 2. 搜索用户
    searchBtn.addEventListener('click', () => {
        const params = {
            username: usernameSearch.value.trim(),
            phone: phoneSearch.value.trim(),
            status: statusSearch.value
        };
        // 移除空参数
        Object.keys(params).forEach(key => {
            if (params[key] === '' || params[key] === null) {
                delete params[key];
            }
        });
        fetchUsers(params);
    });

    // 3. 添加与编辑用户模态框处理
    function openModal(title, user = null) {
        modalTitle.textContent = title;
        userForm.reset();
        userIdInput.value = '';

        if (user) {
            // 编辑模式
            userIdInput.value = user.id;
            usernameInput.value = user.username;
            phoneInput.value = user.phone;
            avatarInput.value = user.avatar;
            statusInput.value = user.status;
            roleIdInput.value = user.roleId;
        } 
        modal.style.display = 'block';
    }

    function closeModal() {
        modal.style.display = 'none';
    }

    addUserBtn.addEventListener('click', () => openModal('添加用户'));
    closeBtn.addEventListener('click', closeModal);
    window.addEventListener('click', (event) => {
        if (event.target === modal) {
            closeModal();
        }
    });

    // 保存用户（添加或更新）
    userForm.addEventListener('submit', async (event) => {
        event.preventDefault();

        const id = userIdInput.value;
        const userData = {
            username: usernameInput.value,
            phone: phoneInput.value,
            avatar: avatarInput.value,
            status: parseInt(statusInput.value),
            roleId: parseInt(roleIdInput.value)
        };

        const url = id ? `${apiBaseUrl}/user/${id}` : `${apiBaseUrl}/user`;
        const method = id ? 'PUT' : 'POST';

        try {
            const response = await fetch(url, {
                method: method,
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(userData)
            });

            if (!response.ok) {
                throw new Error(`网络请求失败！状态码：${response.status}`);
            }

            // 解析Result格式，获取返回的用户数据（可忽略，仅做成功校验）
            const resultData = await handleResponse(response);
            alert(id ? '更新用户成功！' : '新增用户成功！');
            closeModal();
            fetchUsers(); // 刷新列表
        } catch (error) {
            console.error('保存用户失败:', error);
            alert(`保存用户失败：${error.message}`);
        }
    });

    // 4. 编辑和删除用户
    userList.addEventListener('click', async (event) => {
        const target = event.target;
        const id = target.dataset.id;

        if (target.classList.contains('edit-btn')) {
            // 编辑用户：获取用户详情（Result格式）
            try {
                const response = await fetch(`${apiBaseUrl}/user/${id}`);
                if (!response.ok) {
                    throw new Error(`网络请求失败！状态码：${response.status}`);
                }
                // 解析Result格式，获取data中的用户详情
                const user = await handleResponse(response);
                openModal('编辑用户', user);
            } catch (error) {
                console.error(`获取用户(id=${id})详情失败:`, error);
                alert(`加载用户信息失败：${error.message}`);
            }
        }

        if (target.classList.contains('delete-btn')) {
            // 删除用户
            if (confirm(`确定要删除ID为 ${id} 的用户吗？`)) {
                try {
                    const response = await fetch(`${apiBaseUrl}/user/${id}`, {
                        method: 'DELETE'
                    });

                    if (!response.ok) {
                        throw new Error(`网络请求失败！状态码：${response.status}`);
                    }

                    // 解析Result格式，确认删除成功
                    const resultData = await handleResponse(response);
                    alert(`删除用户成功：${resultData || 'ID为'+id+'的用户已删除'}`);
                    fetchUsers(); // 刷新列表
                } catch (error) {
                    console.error(`删除用户(id=${id})失败:`, error);
                    alert(`删除用户失败：${error.message}`);
                }
            }
        }
    });
});