/**
 *
 */
$(function() {
    $('#log-out').click(function() {
        // 清除session
        $.ajax({
            url : "/backups/user/logout",
            type : "post",
            async : false,
            cache : false,
            dataType : 'json',
            success : function(data) {
                if (data.success) {
                    // 清除成功后退出到登录界面
                    window.open('http://localhost:8080/backups/login','_self');
                }
            },
            error : function(data, error) {
                alert(error);
            }
        });
    });
});