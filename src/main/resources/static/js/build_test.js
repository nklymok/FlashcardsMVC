let addRow = function () {
    let listName = 'flashcards'; //list name in Catalog.class
    let fieldsNames = ['question', 'answer']; //field names from Movie.class
    let rowIndex = document.querySelectorAll('.item').length; //we can add mock class to each movie-row

    let row = document.createElement('tr');
    row.id = rowIndex.toString();
    row.classList.add('item');

    fieldsNames.forEach((fieldName) => {
        let col = document.createElement('td');
        if (fieldName === 'id') {
            col.classList.add('d-none'); //field with id - hidden
        }

        let input = document.createElement('input');
        input.type = 'text';
        input.classList.add('form-control');
        input.id = listName + rowIndex + '.' + fieldName;
        input.setAttribute('name', listName + '[' + rowIndex + '].' + fieldName);

        col.appendChild(input);
        row.appendChild(col);
    });

    document.getElementById('flashcardList').appendChild(row);
};

let removeRow = function () {
    let rowIndex = document.querySelectorAll('.item').length - 1;
    document.getElementById(rowIndex.toString()).outerHTML = "";
}
