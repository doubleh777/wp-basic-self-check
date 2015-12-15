var List = {
		"init" : function(){
			this.bind();
		},
		"bind" : function(){
//			1-1. del클래스를 가진 버튼에 del함수를 click이벤트에 바인딩 한다.
//			1-2. more클래스를 가진 버튼에 more함수를 click이벤트에 바인딩 한다.
		},
		"del" : function(){
//			2-1. del클래스를 가진 엘리먼트를 click하면 해당 질문 목록을 삭제할 수 있다.
//			2-2. this의 context가 List객체여야 한다.
		},
		"more" : function(){
//			3-1. more클래스를 가진 엘리먼트를 click하면 Ajax로 비동기 통신을 하여 질문 목록을 10개 가져온다.
//			3-2. 가져온 질문 목록을 template pattern을 사용하여 넣는다.
//			3-3. this의 context가 List객체여야 한다.
//			3-4. 새로 등록한 엘리먼트들로 del클래스를 가진 엘리먼트를 click하면 해당 질문 목록을 삭제할 수 있다.(event delegate pattern활용)
		}
};

List.init();