


function marqueVoiture(){
	reservId = document.getElementById("100").selectedIndex;
        volkswagen= ["polo","golf","passat","jetta","tiguan"];
        Renault = ["clio" , "megan" , "symbole"];
        mercedes= ["classA" , "classG" , "classS"];
        bmw= ["m1" , "m3" , "m5"];
        isuzu= ["dmax"];
        if(reservId == 0)
        {
            document.getElementById("119").innerHTML="<option th:value=\"0\">selectionner une marque</option>";
        }
       
        if(reservId == 1)
        {
			document.getElementById("119").innerHTML="<option th:value=\"0\">selectionner une marque</option>";

            for(i=0;i<volkswagen.length;i++)
            { 
            document.getElementById("119").innerHTML+="<option th:value=\""+volkswagen[i]+"\">"+volkswagen[i]+"</option>";
        }
        
        }
        if(reservId == 2)
        {
			document.getElementById("119").innerHTML="<option th:value=\"0\">selectionner une marque</option>";

            for(i=0;i<Renault.length;i++)
            { 
            document.getElementById("119").innerHTML+="<option th:value="+Renault[i]+"> "+Renault[i]+"</option>";
        }
        }
        if(reservId == 3)
        {
			document.getElementById("119").innerHTML="<option th:value=\"0\">selectionner une marque</option>";

            for(i=0;i<mercedes.length;i++)
            { 
            document.getElementById("119").innerHTML+="<option th:value="+mercedes[i]+">"+mercedes[i]+"</option>";
        }
        }
        if(reservId == 4)
        {
			document.getElementById("119").innerHTML="<option th:value=\"0\">selectionner une marque</option>";

            for(i=0;i<bmw.length;i++)
            { 
            document.getElementById("119").innerHTML+="<option th:value="+bmw[i]+">"+bmw[i]+"</option>";
        }
        }
        if(reservId == 5)
        {
			document.getElementById("119").innerHTML="<option th:value=\"0\">selectionner une marque</option>";

            for(i=0;i<isuzu.length;i++)
            { 
            document.getElementById("119").innerHTML+="<option th:value="+isuzu[i]+">"+isuzu[i]+"</option>";
        }
        }
    }



