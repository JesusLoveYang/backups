/**
 *
 */
$(function() {
    $('.right').click(function() {
        // 获取输入的帐号
        var jobname = $('.jobname').val();
        // 获取输入的密码
        var jobgroup = $('.jobgroup').val();
        var triggername = $('.triggername').val();
        var triggergroup = $('.triggergroup').val();
        var cron = $('.cron').val();

        // 访问后台进行登录验证
        $.ajax({
            url : "/backups/user/insertjob",
            type : "post",
            data : {
                jobname : jobname,
                jobgroup : jobgroup,
                triggername:triggername,
                triggergroup:triggergroup,
                cron:cron,
            },
            success : function(data) {
                if (data.success) {
                    // 若用户在前端展示系统页面则自动链接到前端展示系统首页
                    window.open('http://localhost:8080/backups/success','_self');
                    alert('添加成功！');
                } else {
                    alert('任务内容不能为空');
                }
            }
        });
    });
});