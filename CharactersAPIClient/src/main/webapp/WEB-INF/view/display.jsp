<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>Display All Characters & Phrases </title>
		<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
		
		<!-- For Bootstrap -->
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" >
		<link rel="stylesheet" type="text/css" href="main.css">
		<!-- End For Bootstrap -->
		
		
		<script>
	    	$(document).ready(function () {
				 
				 $.ajax({
						
			            dataType: "json",			
			             url: "http://localhost:8083/getallcharacters",				
			             contentType: "application/json; charset=utf-8",			              				
			             headers: {  "Access-Control-Allow-Origin": "*" },							
			             success:function(data){				             
				             var output="";
				            // $("#phrs").html('');				             
							 var responseData = data;
				             jQuery.each(responseData, function(i,voval){
								$("#quotestable").append("<tr><td>"+voval.firstName+"</td><td>"+voval.age+"</td><td><input type=submit value = 'Delete' id="+voval.firstName+"></td></tr>");
	
						     });
				         },
				         error: function (textStatus, errorThrown) {							
							$("#phrs").html('');
				            $("#phrs").append('<span class="error">No Data Found</span>');
				            Success = false;
				         }
			      });

			      $("form").submit(function(){				  	
				  	 var buttonId = $(document.activeElement).attr('id');
				  	 //alert("Button Id :"+buttonId);

					   $.ajax({
						
			            dataType: "json",			
			             url: "http://localhost:8083/deletecharacters",				
			             contentType: "application/json; charset=utf-8",			              				
			             headers: {  "Access-Control-Allow-Origin": "*" },
			             data : "chardetail="+buttonId,
			             success:function(data){
				             		             
				             $("#phrs").html('');
					         $("#phrs").append("Deleted the Character successfully");
				         },
				         error: function (textStatus, errorThrown) {
							$("#phrs").html('');
							$("#phrs").html("Deletion of Character Failed");
							
				         }    
				     });  
			    });	
	    });	
	 	</script>
	</head>	
	
	<body>
		<form>
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
		 
		<div id="phrs">
		</div>
		
		<div class="table table-hover">
				<table id = "quotestable" class="table-bordered">
					<tbody>
						<tr>
							<td>
								First Name
							</td>
							<td>
								Age
							</td>
							<td>
								Action
							</td>					
						</tr>
					</tbody>
				</table>
			</div>
		</form>
		</body>
		</html>