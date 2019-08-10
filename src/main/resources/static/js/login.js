/**
 *
 */
$(function() {
    $('.loginin').click(function() {
        // 获取输入的帐号
        var userName = $('.username').val();
        // 获取输入的密码
        var password = $('.password').val();

        // 访问后台进行登录验证
        $.ajax({
            url : "/backups/user/login",
            type : "post",
            data : {
                username : userName,
                password : password,
            },
            success : function(data) {
                if (data.success) {
                    // 若用户在前端展示系统页面则自动链接到前端展示系统首页
                    window.open('http://localhost:8080/backups/success','_self');
                    alert('登录成功！');
                } else {
                    alert('用户名或密码不正确');
                }
            }
        });
    });
});