document.addEventListener('DOMContentLoaded', () => {
    // 获取登录表单和输入框元素
    const loginForm = document.getElementById('login-form');
    const usernameInput = document.getElementById('username');
    const passwordInput = document.getElementById('password');

    const apiBaseUrl = 'http://localhost:8080'; // 后端服务地址，按需修改

    // 监听表单提交事件
    loginForm.addEventListener('submit', async (event) => {
        event.preventDefault(); // 阻止表单默认提交行为

        // 组装登录请求参数（和后端接收的SysUserLoginDto字段对应）
        const loginData = {
            username: usernameInput.value.trim(), // 去除首尾空格
            password: passwordInput.value.trim()
        };

        try {
            // 发送登录请求：匹配后端 /auth/login POST接口
            const response = await fetch(`${apiBaseUrl}/auth/login`, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json' // 声明JSON请求体
                },
                body: JSON.stringify(loginData) // 转JSON字符串传递参数
            });

            // 解析后端返回的JSON结果
            const res = await response.json();

            // 登录成功：后端返回code=200
            if (res.code === 200) {
                alert('登录成功！');
                window.location.href = 'index.html'; // 跳转到主页
            } else {
                // 登录失败：后端返回code=500（参数校验失败/账号密码错误）
                alert(`登录失败：${res.message || '用户名或密码错误'}`);
            }
        } catch (error) {
            // 网络错误/服务器无法访问等异常情况
            console.error('登录请求异常:', error);
            alert('登录失败：服务器或网络异常，请稍后重试');
        }
    });
});