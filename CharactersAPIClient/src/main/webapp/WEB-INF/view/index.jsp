<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>Characters & Phrases </title>
		<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
		
		<!-- For Bootstrap -->
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" >
		<link rel="stylesheet" type="text/css" href="main.css">
		<!-- End For Bootstrap -->
		
		
		<script>
			 $(function () {
			    $("#charsubmit").click(function (e) {		
			    	e.preventDefault();				
				 	var charsval = $("#chars").val();

				 	var chardetail  = charsval;
				 	 $(".error").remove();
				 	//alert(chardetail);

				 	if (chardetail.length < 1) {				 	      
				 	      $("#err").html('');				            
		            	  $("#err").append('<span class="error">This field is required</span>');
				 	      return false;
				 	}
	
				         $.ajax({
				
				            dataType: "json",
				
				             url: "http://localhost:8083/getphrase",				
				             contentType: "application/json; charset=utf-8",
				             data : "chardetail="+chardetail, 				
				             headers: {  "Access-Control-Allow-Origin": "*" },

				             success:function(data){
					             //alert("success");
					             $("#phrs").html('');
					             $("#phrs").append(data);

					         },
					         error: function (textStatus, errorThrown) {
								alert("No Data Found");
								$("#phrs").html('');
					            $("#phrs").append('<span class="error">No Data Found</span>');
					            Success = false;
					         }
				      });
				
				     });
			


			 $("#chars").keydown(function(e){
				 	
		        	  $("#err").html('');	
		              var key = e.keyCode;
		              //alert(key);
		              if(key>0){
			              if (!((key == 8) || (key == 32) || (key == 46) || (key >= 35 && key <= 40) || (key >= 65 && key <= 90))) {
			            	  $("#err").html('');				            
			            	  $("#err").append('<span class="error">Enter a valid Name</span>');
			                  e.preventDefault();
			              }
		              }
		         	  
			 });

		});	
	 	</script>
	</head>	
	
	<body>
	<!-- For Bootstrap -->
	
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
	<!-- End For Bootstrap -->
	<nav class="navbar navbar-expand-md">
  		<!-- <a class="navbar-brand" href="#">Logo</a>-->
  		<label for="usr" class="navbar-brand">Characters and Phrases</label>
  		 
 			 <button class="navbar-toggler navbar-dark" type="button" data-toggle="collapse" data-target="#main-navigation">
   			 	<span class="navbar-toggler-icon"></span>
  			  </button>
  		<div class="collapse navbar-collapse" id="main-navigation">
  		  <ul class="navbar-nav">
    		  <li class="nav-item">
     		   <a class="nav-link" href="http://localhost:8082/display">Home</a>
   			   </li>
   			   <li class="nav-item">
   			   <a class="nav-link" href="http://localhost:8082/index">Search</a>
   			   </li>
   			   <li class="nav-item">
    		    <a class="nav-link" href="#">About</a>
     		   </li>
  		 </ul>
  		</div>
	</nav>
	<br>
		Enter the character and get the phrases of the character
		<br><br>
				
	<P class="text-success">Character	 :</P>
		<div class="col-xs-2">
			<input type =text id="chars" class="col-xs-2">
		</div>
		<div id="err">
		</div>
		
		<br>
		 
			<input type = submit id="charsubmit" class="btn btn-success">
		<br>
		<br>	
		<p class="text-primary"><b>Phrases</b></p> 
		<div id="phrs">
		</div>
		
	</body>
</html>