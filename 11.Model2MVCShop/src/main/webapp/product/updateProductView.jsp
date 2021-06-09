<%@ page contentType="text/html; charset=EUC-KR" %>
<%@ page pageEncoding="EUC-KR"%>

<!DOCTYPE html>

<html lang="ko">

<head>
	<meta charset="EUC-KR">
	
	<!--  ///////////////////////// Bootstrap, css ////////////////////////// -->
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	
	<!--  ///////////////////////// Bootstrap, jQuery CDN ////////////////////////// -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" >
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" >
	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" ></script>
	
	<!--  ///////////////////////// calendar.js ////////////////////////// -->
	<script type="text/javascript" src="../javascript/calendar.js"></script>
	
	<!--  ///////////////////////// CSS ////////////////////////// -->
	<style>
       body > div.container{
        	border: 3px solid #D6CDB7;
            margin-top: 10px;
        }
    </style>
    
    <!--  ///////////////////////// JavaScript ////////////////////////// -->
	<script type="text/javascript">
	
	// 수정시 Form 유효성 검사 /////////////////////////////////////////////////////////
	function fncAddProduct(){

	 	var name = document.detailForm.prodName.value;
		var detail = document.detailForm.prodDetail.value;
		var manuDate = document.detailForm.manuDate.value;
		var price = document.detailForm.price.value;
	
		if(name == null || name.length<1){
			alert("상품명은 반드시 입력하여야 합니다.");
			return;
		}
		if(detail == null || detail.length<1){
			alert("상품상세정보는 반드시 입력하여야 합니다.");
			return;
		}
		if(manuDate == null || manuDate.length<1){
			alert("제조일자는 반드시 입력하셔야 합니다.");
			return;
		}
		if(price == null || price.length<1){
			alert("가격은 반드시 입력하셔야 합니다.");
			return;
		}
		
		$("form").attr("method" , "GET").attr("action" , "/product/updateProduct?prodNo=${product.prodNo}").submit();
		
	}
	
	// "수정" 및 "취소" 시 Event /////////////////////////////////////////////////////////
	$(function() {
		
		// "수정" Click event
		 $( "button.btn.btn-primary" ).on("click" , function() {
			self.location = "/product/updateProduct?prodNo=${product.prodNo}"
		});
		
		// "취소" Click event
		$("a[href='#']").on("click" , function() {
			self.location = "/product/listProduct?menu=manage"
		});	
		 
	});
	
	</script>
</head>

<body>
<!-- // ToolBar Start ///////////////////////////////////////////////-->
	<jsp:include page="/layout/toolbar.jsp" />
	
	<div class="navbar  navbar-default">
        <div class="container">
        	<a class="navbar-brand" href="/index.jsp">11.Model2MVCShop</a>
   		</div>
   	</div>
<!-- // ToolBar End /////////////////////////////////////////////////-->

<!-- // 화면구성 div ////////////////////////////////////////////////// -->
	<div class="container">
	
		<h1 class="bg-primary text-center">상 품 수 정</h1>
		
		<div class="page-header text-center">
	       <h5 class="text-muted">상품수정을 원하신다면 입력 후
	       <strong class="text-danger">수정</strong>을 눌러주시고, 상품관리 목록으로 돌아가실 경우
	       <strong class="text-danger">취소</strong>를 눌러주세요</h5>
	    </div>	

<!-- // form Start ////////////////////////////////////////////////// -->
	<form class="form-horizontal" name=updateProductView>

<!-- // 상품명입력 /////////////////////////////////////////////////////-->
	<div class="form-group">
		<label for="prodName" class="col-sm-offset-1 col-sm-3 control-label">상 품 명</label>
		<div class="col-sm-4">
			<input type="text" class="form-control" id="prodName" name="prodName" value="${product.prodName}">
		</div>
	</div>

<!-- // 상품상세정보 /////////////////////////////////////////////////////-->
 	<div class="form-group">
		<label for="prodDetail" class="col-sm-offset-1 col-sm-3 control-label">상품상세정보</label>
		<div class="col-sm-4">
			<input type="text" class="form-control" id="prodDetail" name="prodDetail" value="${product.prodDetail}">
		</div>
	</div>

<!-- // 제조일자 /////////////////////////////////////////////////////-->
 	<div class="form-group">
		<label for="manuDate" class="col-sm-offset-1 col-sm-3 control-label">상품제조일자</label>
			<img src="../images/ct_icon_date.gif" width="15" height="15" 
			onclick="show_calendar('document.updateProductView.manuDate', document.updateProductView.manuDate.value)"/>
		<div class="col-sm-4">
			<input type="text" class="form-control" id="manuDate" name="manuDate" value="${product.manuDate}">
		</div>
	</div>
	
<!-- // 가격 /////////////////////////////////////////////////////-->
  	<div class="form-group">
		<label for="price" class="col-sm-offset-1 col-sm-3 control-label">가 격</label>
		<div class="col-sm-4">
			<input type="text" class="form-control" id="price" name="price" value="${product.price}">
		</div>
	</div>
 	
<!-- // 상품이미지 /////////////////////////////////////////////////////-->
  	<div class="form-group">
		<label for="fileName" class="col-sm-offset-1 col-sm-3 control-label">가 격</label>
		<div class="col-sm-4">
			<input type="text" class="form-control" id="fileName" name="fileName" value="${product.fileName}">
		</div>
	</div>
 	
<!-- // 수정, 취소 버튼 ///////////////////////////////////////////////--> 		  
	  <div class="form-group">
	    <div class="col-sm-offset-4  col-sm-4 text-center">
	      <button type="button" class="btn btn-primary">수&nbsp;정</button>
		  <a class="btn btn-primary btn" href="#" role="button">취&nbsp;소</a>
	    </div>
	  </div>
	</form> 
	
	
	</div>

</body>
</html>