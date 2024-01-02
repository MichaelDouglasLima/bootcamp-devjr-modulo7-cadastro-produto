function convertToNumber(priceFormat) {
    return priceFormat.replace(/\./g, '').replace(',', '.'); //Expressão Regular
}

// Data
var products = [];
var categories = [];

//Onload
loadCategories();
loadProducts();
loadSelectCategories();

//Load all categories
function loadCategories() {

    // Chamada Assíncrona
    // $.getJSON("http://localhost:8080/categories", (response) => {
    //     categories = response;
    // });

    // Chamada Não Assíncrona
    $.ajax({
            url : "http://localhost:8080/categories",
            type : "GET",
            async : false,
            success : (response) => {
                categories = response;
            }
    });
}

//Load all products
function loadProducts() {

    $.getJSON("http://localhost:8080/products", (response) => {

        products = response;

        for (let prod of products) {
            addNewRow(prod);
        }
    });
}

function loadSelectCategories() {
    var select = document.getElementById("selectCategory");
    
    for (let category of categories) {
        var option = document.createElement("option");
        
        option.value = category.id;
        option.text = category.name;
        select.appendChild(option);
    }
}

//Save a product
function save() {
    var prod = {
        id: products.length + 1,
        name: document.getElementById("inputName").value,
        description: document.getElementById("inputDescription").value,
        price: convertToNumber(document.getElementById("inputPrice").value),
        category: document.getElementById("selectCategory").value,
        promotion: document.getElementById("checkBoxPromotion").checked,
        new: document.getElementById("checkBoxNewProduct").checked
    };

    addNewRow(prod);
    products.push(prod);
    document.getElementById("formProduct").reset();
}

//Add new Row
function addNewRow(prod) {
    var table = document.getElementById("productsTable");

    var newRow = table.insertRow();

    //Insert product id
    var idNode = document.createTextNode(prod.id);
    newRow.insertCell().appendChild(idNode);

    //Insert product name
    var nameNode = document.createTextNode(prod.name);
    newRow.insertCell().appendChild(nameNode);

    //Insert product description
    var descriptionNode = document.createTextNode(prod.description);
    var cell = newRow.insertCell();
    cell.className = "d-none d-md-table-cell";
    cell.appendChild(descriptionNode);

    //Insert product price
    var formatter = new Intl.NumberFormat("pt-BR", { style: "currency", currency: "BRL" });

    var priceNode = document.createTextNode(formatter.format(prod.price));
    newRow.insertCell().appendChild(priceNode);

    //Insert product category
    var categoryNode = document.createTextNode(categories[prod.idCategory - 1].name);
    newRow.insertCell().appendChild(categoryNode);

    //Insert product options
    var options = "";

    if (prod.promotion) {
        options = "<span class='badge bg-success me-1'>P</span>";
    }

    if (prod.newProduct) {
        options += "<span class='badge bg-primary'>L</span>";
    }

    cell = newRow.insertCell();
    cell.className = "d-none d-md-table-cell";
    cell.innerHTML = options;

}