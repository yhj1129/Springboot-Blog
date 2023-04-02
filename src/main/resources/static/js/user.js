let index = {
    init: function (){
        $("#btn-save").on("click", ()=>{ //this를 바인딩하기 위해서 화살표 함수를 사용했음
            this.save();
        });
        // $("#btn-login").on("click", ()=>{ //this를 바인딩하기 위해서 화살표 함수를 사용했음
        //     this.login();
        // });
    },

    save: function (){
        //alert('user의 save함수 호출됨');

        let data = {
            username: $("#username").val(),
            password: $("#password").val(),
            email: $("#email").val()
        };

        //console.log(data);
        //ajax 호출시 default가 비동기 호출
        // ajax가 통신을 성공하고 서버가 json을 리턴해주면 자동으로 자바 오브젝트로 변환해줌! dataType: "json" 필요 없음
        $.ajax({
            //회원가입 수행 요청
            type: "POST",
            url: "/auth/joinProc",
            data: JSON.stringify(data), //json으로 데이터 보내기 body데이터로
            contentType: "application/json; charset=utf-8", //body 데이터의 타입
            dataType: "json" //서버로부터 받을 응답의 타입  기본적으로 string인데 json같이 생겼으면  -> 자바스크립트 오브젝트로 변환해서 받음
        }).done(function (resp){
            //성공하면 수행되는 곳
            alert("회원가입이 완료되었습니다");
            //console.log(resp);
            location.href = "/";
        }).fail(function(error) {
            //실패하면 수행되는 곳
            alert(JSON.stringify(error));
        });//ajax 통신을 이용해서 3개의 데이터를 json으로 변경하여 insert 요청
    }

    // login: function (){
    //     //alert('user의 save함수 호출됨');
    //
    //     let data = {
    //         username: $("#username").val(),
    //         password: $("#password").val()
    //     };
    //
    //     //console.log(data);
    //     //ajax 호출시 default가 비동기 호출
    //     // ajax가 통신을 성공하고 서버가 json을 리턴해주면 자동으로 자바 오브젝트로 변환해줌! dataType: "json" 필요 없음
    //     $.ajax({
    //         //회원가입 수행 요청
    //         type: "POST",
    //         url: "/api/user/login",
    //         data: JSON.stringify(data), //json으로 데이터 보내기 body데이터로
    //         contentType: "application/json; charset=utf-8", //body 데이터의 타입
    //         dataType: "json" //서버로부터 받을 응답의 타입  기본적으로 string인데 json같이 생겼으면  -> 자바스크립트 오브젝트로 변환해서 받음
    //     }).done(function (resp){
    //         //성공하면 수행되는 곳
    //         alert("로그인이 완료되었습니다");
    //         //console.log(resp);
    //         location.href = "/";
    //     }).fail(function(error) {
    //         //실패하면 수행되는 곳
    //         alert(JSON.stringify(error));
    //     });//ajax 통신을 이용해서 3개의 데이터를 json으로 변경하여 insert 요청
    // }
}
index.init();