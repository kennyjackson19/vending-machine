<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login Page</title>
</head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<script>

$(document).ready(function(){
	$( "#getsoda" ).prop( "disabled", true );
	$('#numberSoda').hide();
});
function validateQuater(){
	 if($('#quaterCount').val() > 0){
		 $("#error").html("");
		 $.ajax({
			  url: "http://localhost:8082/validateQuater/"+$("#quaterCount").val(),
			  success: function(ItemData){
			  // alert(ItemData.canBuyCount);
			  if(ItemData.count > 0){
				  if(ItemData.canBuyCount > 0){
					  $( "#getsoda" ).prop( "disabled", false );
					  $( "#validateQuater" ).prop( "disabled", true );
					  $( "#quaterCount" ).prop( "disabled", true );
					 var balanceMessage = ItemData.balance > 0 ? "Your balance amount is "+ItemData.balance : "";
					 $("#info").html(balanceMessage);
					 $('#numberSoda').show();
			  		}else{
			  			$("#info").html(" Insufficient Funds !!!");
			  			 $("#success").html("");
			  		}
			   }else{
				   $("#error").html(" No Soda available !!!");
				   $("#success").html("");
			   }
				  
			  }
			});	
	 }else{
		  $("#error").html(" Enter Quater !!!");
	 }
}
function getSoda(){ 
		 //alert($('#sodaCount').val());
		 if($('#sodaCount').val() > 0){
			 $.ajax({
				  url: "http://localhost:8082/getSoda/"+$("#count").val()+"/"+$("#quaterCount").val(),
				  success: function(sodaCount){
					  //alert("sodaCount:: "+sodaCount);				   
				    $("#sodaValue").html("Soda's available : "+sodaCount);
				       // alert(sodaCount);
				       if($("#sodaCount").val() - sodaCount > 0){
				    	   $('input[name="sodaCount"]').attr('value',sodaCount);
				    	   $("#success").html(" Dispensing Soda !!!");
				    	   $("#info").html("");
				    	   $( "#getsoda" ).prop( "disabled", true );
				       }else{
				    	   $("#info").html(" Insufficient Funds !!!");
				       }
				        if(sodaCount == 0){
				        	$("#error").html(" No more Soda available !!!");
				        }
				  }
				});	
		 }else{
			 $("#error").html(" Please wait for restock !!!");
		 }
	 }
</script>


<body>
<h1>Soda Vending Machine</h1></br>
 <span name="error" id="error" style="color:red"></span></br>
 <span name="info" id="info" style="color:orange"></span></br>
 <span name="success" id="success" style="color:green"></span></br>
 <span name="sodaValue" id="sodaValue">Soda's available : ${sodaCount}</span>
<table>
<tr>
<td>Insert quater</td>
<td><input type = "text" id="quaterCount" name="quaterCount"></input></td>
<td><button type="Submit" name ="validateQuater" id ="validateQuater" onClick="validateQuater()">Check for Soda</button></td>
</tr>
</table>
<div id="numberSoda">
</table>
<tr>
<td>Enter the no.of Soda: </td>
<td><input type = "text" id="count" name="count"></input></td>
<td><button type="Submit" id="getsoda" name ="getsoda" onClick="getSoda()">Get Soda</button></td>
</tr>
</table>
</div id="numberSoda">

<input id="sodaCount" name="sodaCount" type="hidden" value="${sodaCount}"/></br>

<a href= "/logout">logout</a></br>
</body>
</html>