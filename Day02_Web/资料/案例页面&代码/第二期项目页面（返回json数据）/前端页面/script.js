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

    const apiBaseUrl = 'http://localhost:8080'; // 请根据实际后端地址修改

    // 通用响应处理器：解析 Result 格式，判断业务状态码
    async function handleApiResponse(response) {
        let result;
        try {
            result = await response.json();
        } catch (e) {
            throw new Error('服务器返回数据格式错误，请联系管理员');
        }

        if (result.code !== 200) {
            throw new Error(result.message || `业务请求失败（状态码：${result.code}）`);
        }
        return result.data;
    }

    // 1. 获取并展示用户列表
    async function fetchUsers(params = {}) {
        const query = new URLSearchParams(params).toString();
        const url = `${apiBaseUrl}/user/list?${query}`;

        try {
            const response = await fetch(url);
            const users = await handleApiResponse(response);
            renderUsers(users);
        } catch (error) {
            console.error('获取用户列表失败:', error);
            userList.innerHTML = `<tr><td colspan="9" style="text-align:center; color: red;">${error.message || '加载用户数据失败'}</td></tr>`;
        }
    }

    // 渲染用户列表到表格（核心：图片失败则不显示）
    function renderUsers(users) {
        userList.innerHTML = '';
        if (!users || users.length === 0) {
            userList.innerHTML = '<tr><td colspan="9" style="text-align:center;">暂无数据</td></tr>';
            return;
        }

        users.forEach(user => {
            const row = document.createElement('tr');

            // 头像处理：仅有有效地址才渲染img，失败则清空不显示
            let avatarHtml = ''; // 默认空（不显示任何内容）
            if (user.avatar && user.avatar.trim() !== '') {
                // 加载失败时：清空src + 隐藏图片，彻底不显示
                avatarHtml = `<img src="${user.avatar}" alt="头像" style="width: 50px; height: 50px; border-radius: 50%;" 
                                onerror="this.src=''; this.style.display='none'; this.onerror=null;">`;
            }

            // 时间格式化（兼容空值）
            const formatTime = (timeStr) => {
                if (!timeStr) return '';
                try {
                    return new Date(timeStr).toLocaleString();
                } catch (e) {
                    return timeStr;
                }
            };

            row.innerHTML = `
                <td>${user.id || ''}</td>
                <td>${user.username || ''}</td>
                <td>${user.phone || ''}</td>
                <td>${avatarHtml}</td> <!-- 有有效地址才显示图，失败则空 -->
                <td>${user.status === 1 ? '正常' : (user.status === 0 ? '禁用' : '')}</td>
                <td>${user.roleId || ''}</td>
                <td>${formatTime(user.createTime)}</td>
                <td>${formatTime(user.updateTime)}</td>
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
        Object.keys(params).forEach(key => {
            if (params[key] === '' || params[key] === null || params[key] === undefined) {
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
            userIdInput.value = user.id || '';
            usernameInput.value = user.username || '';
            phoneInput.value = user.phone || '';
            avatarInput.value = user.avatar || '';
            statusInput.value = user.status !== undefined ? user.status : 1;
            roleIdInput.value = user.roleId || '';
        } else {
            statusInput.value = 1;
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

    // 4. 保存用户（添加或更新）
    userForm.addEventListener('submit', async (event) => {
        event.preventDefault();

        const id = userIdInput.value;
        const userData = {
            username: usernameInput.value.trim(),
            phone: phoneInput.value.trim(),
            avatar: avatarInput.value.trim() || '',
            status: parseInt(statusInput.value),
            roleId: parseInt(roleIdInput.value)
        };

        if (!userData.username) {
            alert('请输入用户名！');
            return;
        }
        if (!userData.phone) {
            alert('请输入手机号！');
            return;
        }
        if (isNaN(userData.roleId) || userData.roleId <= 0) {
            alert('请输入有效的角色ID！');
            return;
        }

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
            await handleApiResponse(response);
            alert(id ? '用户更新成功！' : '用户添加成功！');
            closeModal();
            fetchUsers();
        } catch (error) {
            console.error('保存用户失败:', error);
            alert(`保存用户失败：${error.message}`);
        }
    });

    // 5. 编辑和删除用户
    userList.addEventListener('click', async (event) => {
        const target = event.target;
        const id = target.dataset.id;

        if (target.classList.contains('edit-btn')) {
            if (!id) {
                alert('用户ID不能为空！');
                return;
            }
            try {
                const response = await fetch(`${apiBaseUrl}/user/${id}`);
                const user = await handleApiResponse(response);
                openModal('编辑用户', user);
            } catch (error) {
                console.error(`获取用户(id=${id})详情失败:`, error);
                alert(`加载用户信息失败：${error.message}`);
            }
        }

        if (target.classList.contains('delete-btn')) {
            if (!id) {
                alert('用户ID不能为空！');
                return;
            }
            if (confirm(`确定要删除ID为 ${id} 的用户吗？`)) {
                try {
                    const response = await fetch(`${apiBaseUrl}/user/${id}`, {
                        method: 'DELETE'
                    });
                    await handleApiResponse(response);
                    alert(`用户ID ${id} 删除成功！`);
                    fetchUsers();
                } catch (error) {
                    console.error(`删除用户(id=${id})失败:`, error);
                    alert(`删除用户失败：${error.message}`);
                }
            }
        }
    });
});