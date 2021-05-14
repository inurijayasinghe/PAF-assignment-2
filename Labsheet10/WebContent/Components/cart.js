$(document).ready(function()
	{
	if ($("#alertSuccess").text().trim() == "")
	{
	$("#alertSuccess").hide();
	}
	$("#alertError").hide();
	});
	
// SAVE ============================================
	$(document).on("click", "#btnSave", function(event)
	{
		// Clear alerts---------------------
		$("#alertSuccess").text("");
		$("#alertSuccess").hide();
		$("#alertError").text("");
		$("#alertError").hide();
		
		// Form validation-------------------
	    var status = validatecartForm();
		if (status != true)
		{
		$("#alertError").text(status);
		$("#alertError").show();
		return;
		}
		
		 // If valid------------------------
		 var type = ($("#hidprodnumSave").val() == "") ? "POST" : "PUT"; 
		 $.ajax( 
		 { 
		 url : "cartAPI", 
		 type : type, 
		 data : $("#formcart").serialize(), 
		 dataType : "text", 
		 complete : function(response, status) 
		 { 
		 oncartSaveComplete(response.responseText, status); 
		 } 
 	}); 
});
		
// UPDATE==========================================
	$(document).on("click", ".btnUpdate", function(event)
	{
	$("#hidprodnumSave").val($(this).data("prodnum"));
	$("#proid").val($(this).closest("tr").find('td:eq(0)').text());
	$("#prodname").val($(this).closest("tr").find('td:eq(1)').text());
	$("#prodqty").val($(this).closest("tr").find('td:eq(2)').text());
	$("#prodprice").val($(this).closest("tr").find('td:eq(3)').text());
	});
	
// DELETE===========================================
	$(document).on("click", ".btnRemove", function(event)
	{ 
	 $.ajax( 
	 { 
	 url : "cartAPI", 
	 type : "DELETE", 
	 data : "prodnum=" + $(this).data("prodnum"),
	 dataType : "text", 
	 complete : function(response, status) 
	 { 
	 oncartDeleteComplete(response.responseText, status); 
	 } 
	 }); 
});
// CLIENT-MODEL================================================================
function validatecartForm()
	{
	// CODE
	if ($("#prodid").val().trim() == "")
	{
	return "Insert product id.";
	}
	// NAME
	if ($("#prodname").val().trim() == "")
	{
	return "Insert prod name.";
	}
	// qty
	if ($("#prodqty").val().trim() == "")
	{
	return "Insert product quantity.";
	}
	// price
	if ($("#prodprice").val().trim() == "")
	{
	return "Insert product price.";
	}
	// PRICE-------------------------------
	//if ($("#itemPrice").val().trim() == "")
	//{
	//return "Insert Item Price.";
	//}
	// is numerical value
	//var tmpPrice = $("#itemPrice").val().trim();
	//if (!$.isNumeric(tmpPrice))
	//{
	//return "Insert a numerical value for Item Price.";
	//}
	// convert to decimal price
	//$("#itemPrice").val(parseFloat(tmpPrice).toFixed(2));
	// DESCRIPTION------------------------
	//if ($("#itemDesc").val().trim() == "")
	//{
	//return "Insert Item Description.";
	//}
	return true;
}

function onItemSaveComplete(response, status)
	{ 
	if (status == "success") 
	 { 
	 var resultSet = JSON.parse(response); 
	 if (resultSet.status.trim() == "success") 
	 { 
	 $("#alertSuccess").text("Successfully saved."); 
	 $("#alertSuccess").show();
	 $("#divItemsGrid").html(resultSet.data); 
	 } else if (resultSet.status.trim() == "error") 
	 { 
	 $("#alertError").text(resultSet.data); 
	 $("#alertError").show(); 
	 } 
	 } else if (status == "error") 
	 { 
	 $("#alertError").text("Error while saving."); 
	 $("#alertError").show(); 
	 } else
	 { 
	 $("#alertError").text("Unknown error while saving.."); 
	 $("#alertError").show(); 
	 } 
	 $("#hidItemIDSave").val(""); 
	 $("#formItem")[0].reset(); 
}

function onItemDeleteComplete(response, status)
	{ 
	if (status == "success") 
	 { 
	 var resultSet = JSON.parse(response); 
	 if (resultSet.status.trim() == "success") 
	 { 
	 $("#alertSuccess").text("Successfully deleted."); 
	 $("#alertSuccess").show();
	 $("#divItemsGrid").html(resultSet.data); 
	 } else if (resultSet.status.trim() == "error") 
	 { 
	 $("#alertError").text(resultSet.data); 
	 $("#alertError").show(); 
	 } 
	 } else if (status == "error") 
	 { 
	 $("#alertError").text("Error while deleting."); 
	 $("#alertError").show(); 
	 } else
	 { 
	 $("#alertError").text("Unknown error while deleting.."); 
	 $("#alertError").show(); 
 } 
}




