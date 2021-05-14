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
var status = validateItemForm();
	if (status != true)
	{
	$("#alertError").text(status);
	$("#alertError").show();
	return;
	}
// If valid------------------------
	$("#formItem").submit();
	});
// UPDATE==========================================
	$(document).on("click", ".btnUpdate", function(event)
	{
	$("#hidprodnumSave").val($(this).closest("tr").find('#hidprodnumUpdate').val());
	$("#prodid").val($(this).closest("tr").find('td:eq(0)').text());
	$("#prodname").val($(this).closest("tr").find('td:eq(1)').text());
	$("#prodqty").val($(this).closest("tr").find('td:eq(2)').text());
	$("#prodprice").val($(this).closest("tr").find('td:eq(3)').text());
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
	return "Insert product Name.";
	}

	// PRICE-------------------------------
	if ($("#prodprice").val().trim() == "")
	{
	return "Insert product Price.";
	}
	// is numerical value
	var tmpPrice = $("#prodprice").val().trim();
	if (!$.isNumeric(tmpPrice))
	{
	return "Insert a numerical value for product Price.";
	}
	// convert to decimal price
	$("#prodprice").val(parseFloat(tmpPrice).toFixed(2));
	// DESCRIPTION------------------------
	if ($("#prodqty").val().trim() == "")
	{
	return "Insert product quantity.";
	}
	return true;
}