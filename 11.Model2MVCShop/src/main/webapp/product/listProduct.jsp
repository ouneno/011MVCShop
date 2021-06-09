<%@ page contentType="text/html; charset=EUC-KR" %>
<%@ page pageEncoding="EUC-KR"%>

<!--  ///////////////////////// JSTL  ////////////////////////// -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>

<html lang="ko">
	
<head>
	<meta charset="EUC-KR">
	
	<!-- 참조 : http://getbootstrap.com/css/   참조 -->
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	
	<!--  ///////////////////////// Bootstrap, jQuery CDN ////////////////////////// -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" >
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" >
	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" ></script>
	
	
	<!-- Bootstrap Dropdown Hover CSS -->
   <link href="/css/animate.min.css" rel="stylesheet">
   <link href="/css/bootstrap-dropdownhover.min.css" rel="stylesheet">
    <!-- Bootstrap Dropdown Hover JS -->
   <script src="/javascript/bootstrap-dropdownhover.min.js"></script>
   
   
   <!-- jQuery UI toolTip 사용 CSS-->
  <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
  <!-- jQuery UI toolTip 사용 JS-->
  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
	
	<!--  ///////////////////////// CSS ////////////////////////// -->
	<style>
	  body {
            padding-top : 50px;
        }
    </style>
	
	<!--  ///////////////////////// JavaScript ////////////////////////// -->
	<script type="text/javascript">
	
	//============= Getlist =============//	
	function fncGetList(currentPage) {
		$("#currentPage").val(currentPage)
		$("form").attr("method" , "POST").attr("action" , "/product/listProduct?menu=${! empty menu && menu == 'manage' ? 'manage' : 'search'}").submit();
	}
	
		//============= "검색" Event 처리 =============//	
		 $(function() {
			 $( "button.btn.btn-default" ).on("click" , function() {
				fncGetList(1);
			});
		 });
		
		//============= 상품명 "Click" 시 상품정보보기 Event =============	
		 $(function() {
		
			// '.manage' Click 시 Event
			$( '.manage' ).on("click" , function() {
				self.location = "/product/updateProductView?prodNo="+$(this).attr('prodNo');	
			});
			
			// '.manage' Click 시 Event
			$( '.search' ).on("click" , function() {
				self.location = "/product/getProduct?prodNo="+$(this).attr('prodNo');
			});
		 
		 
		 });			
	
		//=================== "글자 색상 및 배경설정" Event ===================//
		$(function() {		
			//$("td:nth-child(2)").css("background-color" , "whitesmoke");		
			$("td:nth-child(5n+2)").css("color" , "red");
			$("h7").css("color" , "red");	
		});
		
		//=================== "manage, search" Link Event ===================//			
		$(function(){
			$("td:nth-child(6) > i").on("click" , function() {
			//alert(".search 상품검색진입");
			
				var prodNo = $(this).next().val();
				//alert("prodNo : "+prodNo)
			
				$.ajax(
						{
							url : "/product/json/getProduct/"+prodNo ,
							method : "GET" ,
							dataType : "json" ,
							headers : {
								"Accept" : "application/json" ,
								"Content-Type" : "application/json"
							},
							success : function(JSONData, status) {
								//alert(status)
								
								var displayValue = "<h6>"
														+"상품명	: "+JSONData.prodName+"<br/>"
														+"상세정보	: "+JSONData.prodDetail+"<br/>"
														+"제조일자	: "+JSONData.manuDate+"<br/>"
														+"가 격	: "+JSONData.price+" 원<br/>"
														+"이미지	: "+JSONData.fileName+"<br/>"
														+"</h6>";		
								//alert("displayValue : "+displayValue);
								
								$("h6").remove();
								$( "#"+prodNo+"" ).html(displayValue);
								//alert(".search 상품검색완료");
							}
						});
			});
		});
		
</script>

</head>
<body>

	<!-- ToolBar Start /////////////////////////////////////-->
	<jsp:include page="/layout/toolbar.jsp" />
   	<!-- ToolBar End ///////////////////////////////////////-->

	<!--  화면구성 div Start /////////////////////////////////////-->
	<div class="container">
		
		<!-- 상품관리조회 -->
		<div class="page-header text-info">
	       <h3><option value="manage">${ ! empty menu && menu=='manage' ? "상품관리" : "상품목록조회"}</option></h3>
	    </div>

	    <!-- table 위쪽 검색 Start /////////////////////////////////////-->
	    <div class="row">
	    
	    <!-- 총 게시물 수, 현재 페이지 -->
	    <div class="col-md-6 text-left">
			<p class="text-primary">
				전체  ${resultPage.totalCount} 건수, 현재 ${resultPage.currentPage} 페이지
			</p>
		</div>

			<div class="col-md-6 text-right">
				<form class="form-inline" name="detailForm">
				

					<div class="form-group">
						<select class="form-control" name="searchCondition">
							<option value="0"	${ ! empty search.searchCondition && search.searchCondition==0 ? "selected" : "" }>상품번호</option>
							<option value="1"	${ ! empty search.searchCondition && search.searchCondition==1 ? "selected" : "" }>상품명</option>
						</select>
					</div>

					<div class="form-group">
						<label 	class="sr-only"	for="searchKeyword">검색어</label> 
						<input 	type="text"		class="form-control"  id="searchKeyword" name="searchKeyword" placeholder="검색어"
								value="${! empty search.searchKeyword ? search.searchKeyword : '' }">
					</div>

					<button type="button" class="btn btn-default">검색</button>

					<!-- PageNavigation 선택 페이지 값을 보내는 부분 -->
					<input type="hidden" id="currentPage" name="currentPage" value="" />

				</form>
			</div>
			
		</div>
	<!-- Table 상단 검색 끝 /////////////////////////////////////-->	

	<!-- ///// Table 검색 ///////////////////////////////////// -->
	<table class="table table-hover table-striped" >
	
		<thead>
			<tr>
				<th align="center">No</th>
				<th align="center">상품명<br><h7>(Name Cliek : 상세정보)</h7></th>
				<th align="center">가격</th>
				<th align="center">제조일자</th>
				<th align="center">배송상태</th>
				<th align="center">간략정보</th>
			</tr>
		</thead>

		<tbody>
			<c:set var="i" value="0" />
			<c:forEach var="product" items="${list}">
				<c:set var="i" value="${ i+1 }" />
				<tr>
					<td align="center">${ i }</td>
					<td align="left">
						<c:choose>		
							<c:when test = "${menu == 'manage'}">					
								<div class="manage" prodNo="${product.prodNo}">${product.prodName}</div>
						</c:when>
				
							<c:when test = "${menu == 'search'}">
								<div class="search" prodNo="${product.prodNo}">${product.prodName}</div>
							</c:when>				
						</c:choose>  
					</td>
					<td align="left">${product.price}</td>
					<td align="left">${product.regDate}</td>
					<td align="left">업데이트중</td>
					
					<td align="left">
						<i class="glyphicon glyphicon-ok"	id="${product.prodNo}"></i>
						<input type="hidden"				value="${product.prodNo}">
					</td>				
				</tr>
			</c:forEach>
		</tbody>
	
	</table>
	</div>

 	<!-- PageNavigation Start... -->
	<jsp:include page="../common/pageNavigator_new.jsp"/>
	<!-- PageNavigation End... -->
	
</body>

</html>
