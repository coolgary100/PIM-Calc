function calculatePIM() {
	var transLow1 = document.getElementsByName('transLow1')[0].value;
	var transHigh1 = document.getElementsByName('transHigh1')[0].value;
	var receiveLow1 = document.getElementsByName('receiveLow1')[0].value;
	var receiveHigh1 = document.getElementsByName('receiveHigh1')[0].value;
	var transLow2 = document.getElementsByName('transLow2')[0].value;
	var transHigh2 = document.getElementsByName('transHigh2')[0].value;
	var receiveLow2 = document.getElementsByName('receiveLow2')[0].value;
	var receiveHigh2 = document.getElementsByName('receiveHigh2')[0].value;
	var transLow3 = document.getElementsByName('transLow3')[0].value;
	var transHigh3 = document.getElementsByName('transHigh3')[0].value;
	var receiveLow3 = document.getElementsByName('receiveLow3')[0].value;
	var receiveHigh3 = document.getElementsByName('receiveHigh3')[0].value;
	var hits = 0;
	//var freq1 = transLow;
	//var freq2 = transLow + 1;
	var diff = 0;

	/*var receive1 = [receiveLow1, receiveHigh1];
	var receive2 = [receiveLow2, receiveHigh2];
	var receive3 = [receiveLow3, receiveHigh3];
	var receive4 = [receiveLow4, receiveHigh4];
	*/
	var pim1 = 2*transLow1 - transHigh2;
	var pim2 = 2*transLow1 - transHigh3;
	var pim3 = 2*transLow2 - transHigh3;
	var pim4 = 2*transHigh1 - transLow2;
	var pim5 = 2*transHigh1 - transLow3;
	var pim6 = 2*transHigh2 - transLow3;
	
	if(((receiveHigh1 >= pim1)&&(pim1 >= receiveLow1))||((receiveLow1 <= pim4)&&(pim4 <= receiveHigh1))||(((pim1 <= receiveLow1)&&(pim4 >= receiveHigh1)))) hits++;
	if(((receiveHigh2 >= pim1)&&(pim1 >= receiveLow2))||((receiveLow2 <= pim4)&&(pim4 <= receiveHigh2))||(((pim1 <= receiveLow2)&&(pim4 >= receiveHigh2)))) hits++;
	if(((receiveHigh3 >= pim1)&&(pim1 >= receiveLow3))||((receiveLow3 <= pim4)&&(pim4 <= receiveHigh3))||(((pim1 <= receiveLow3)&&(pim4 >= receiveHigh3)))) hits++;
	if(((receiveHigh1 >= pim2)&&(pim2 >= receiveLow1))||((receiveLow1 <= pim5)&&(pim5 <= receiveHigh1))||(((pim2 <= receiveLow1)&&(pim5 >= receiveHigh1)))) hits++;
	if(((receiveHigh2 >= pim2)&&(pim2 >= receiveLow2))||((receiveLow2 <= pim5)&&(pim5 <= receiveHigh2))||(((pim2 <= receiveLow2)&&(pim5 >= receiveHigh2)))) hits++;
	if(((receiveHigh3 >= pim2)&&(pim2 >= receiveLow3))||((receiveLow3 <= pim5)&&(pim5 <= receiveHigh3))||(((pim2 <= receiveLow3)&&(pim5 >= receiveHigh3)))) hits++;
	if(((receiveHigh1 >= pim3)&&(pim3 >= receiveLow1))||((receiveLow1 <= pim6)&&(pim6 <= receiveHigh1))||(((pim3 <= receiveLow1)&&(pim6 >= receiveHigh1)))) hits++;
	if(((receiveHigh2 >= pim3)&&(pim3 >= receiveLow2))||((receiveLow2 <= pim6)&&(pim6 <= receiveHigh2))||(((pim3 <= receiveLow2)&&(pim6 >= receiveHigh2)))) hits++;
	if(((receiveHigh3 >= pim3)&&(pim3 >= receiveLow3))||((receiveLow3 <= pim6)&&(pim6 <= receiveHigh3))||(((pim3 <= receiveLow2)&&(pim6 >= receiveHigh3)))) hits++;

	//hits++;
	
	document.getElementsByName('output')[0].value = hits;
}
	
	
	