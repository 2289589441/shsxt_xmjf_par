$(function () {
    $(".validImg").click(function () {
        $(this).attr("src", ctx + "/image");
    })



    $("#clickMes").click(function () {
        var phone=$("#phone").val();
        var code=$("input[name='code']").val();
        if(null==phone||""==phone||""==phone.trim()||phone=="undefined"){
            layer.tips("请输入手机号!","#phone");
            return;
        }
        if(isEmpty(code)){
            layer.tips("请输入图片验证码!","input[name='code']");
            return;
        }
        console.log(phone+","+code);

       $.ajax({
            type:"post",
            url:ctx+"/sms",
            data:{
                phone:phone,
                imageCode:code,
                type:1
            },
            dataType:"json",
            success:function (data) {
                if (data.code === 200){
                    console.log(data)
                }
            }
        })

    })


});
