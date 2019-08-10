/**
 *
 */
$(function() {
    getlist();
    function getlist(e) {
        $.ajax({
            url : "/backups/user/getjoblist",
            type : "get",
            dataType : "json",
            success : function(data) {
                if (data.success) {
                    handleUser(data.user);
                    handleList(data.jobList);
                }
            }
        });
    }
    function handleUser(data) {
        $('#user-name').text(data.userName);
    }

    function handleList(data) {
        var html = '';
        data.map(function(item, index) {
            html += '<div class="row row-product product-wrap"><div class="col-40">'
                + item.jobName + '</div><div class="col-20">' +
                "NOSTART"+'</div>'
                + '<div class="col-30">'
                + goJob1(item.jobName)
                + goJob2(item.jobName)
                + goJob3(item.jobName)
                + goJob4(item.jobName) + '</div></div>';

        });
        $('.job-wrap').html(html);
    }

    function goJob1(jobName) {
        if (jobName != null) {
            return '<a class="launch" datatype='+jobName+'>发起</a>';
        } else {
            return '';
        }
    }
    function goJob2(jobName) {
        if (jobName != null) {
            return '<a class="pause" datatype='+jobName+'>暂停</a>';
        } else {
            return '';
        }
    }
    function goJob3(jobName) {
        if (jobName != null) {
            return '<a class="resume" datatype='+jobName+'>恢复</a>';
        } else {
            return '';
        }
    }
    function goJob4(jobName) {
        if (jobName != null) {
            return '<a class="delete" datatype='+jobName+'>删除</a>';
        } else {
            return '';
        }
    }

    $('.job-wrap').delegate('.launch','click',function(e){
        console.log('1')
        var jobname = $(e.target).attr('datatype')
        $.ajax({
            url:'/backups/job/addjob',
            data:{
                jobname : jobname,
            },
            success:function(data){
                console.log($(e.target))
                $(e.target).closest('.product-wrap').find('.col-20').text('执行中...')
            }
        })
        e.preventDefault();
    })

    $('.job-wrap').delegate('.pause','click',function(e){
        console.log('2')
        var jobname = $(e.target).attr('datatype')
        $.ajax({
            url:'/backups/job/pausejob',
            data:{
                jobname : jobname,
            },
            success:function(data){
                console.log($(e.target))
                $(e.target).closest('.product-wrap').find('.col-20').text('已暂停')
            }
        })
        e.preventDefault();
    })

    $('.job-wrap').delegate('.resume','click',function(e){
        console.log('3')
        var jobname = $(e.target).attr('datatype')
        $.ajax({
            url:"/backups/job/resumejob",
            data:{
                jobname : jobname,
            },
            success:function(data){
                console.log($(e.target))
                $(e.target).closest('.product-wrap').find('.col-20').text('已恢复')
            }
        })
        e.preventDefault();
    })

    $('.job-wrap').delegate('.delete','click',function(e){
        console.log('4')
        var jobname = $(e.target).attr('datatype')
        $.ajax({
            url:"/backups/job/deletejob",
            data:{
                jobname : jobname,
            },
            success:function(data){
                console.log($(e.target))
                $(e.target).closest('.product-wrap').find('.col-20').text('已删除')
            }
        })
        e.preventDefault();
    })
});