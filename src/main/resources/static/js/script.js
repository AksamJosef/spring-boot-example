// Функция для получения сотрудников и отображения их в таблице
async function fetchEmployees() {
    try {
        const response = await fetch('http://localhost:8080/employee/get-all');
        if (!response.ok) {
            throw new Error('Ошибка при загрузке данных с сервера');
        }
        const employees = await response.json();
        displayEmployees(employees);
    } catch (error) {
        console.error('Ошибка:', error);
    }
}

// Функция для отображения модального окна редактирования сотрудника
function showEditEmployeeModal(employee) {
    const modalBody = document.querySelector('#editEmployeeModal .modal-body');
    const form = document.createElement('form');
    form.id = 'editEmployeeForm';

    // Добавляем поля для каждого параметра сотрудника
    Object.entries(employee).forEach(([key, value]) => {
        const inputGroup = document.createElement('div');
        inputGroup.classList.add('form-group');
        inputGroup.innerHTML = `
      <label for="${key}">${key}</label>
      <input type="text" class="form-control" id="${key}" value="${value}" ${key === 'id' ? 'disabled' : ''}>
    `;
        form.appendChild(inputGroup);
    });

    modalBody.innerHTML = ''; // Очищаем содержимое модального окна перед добавлением новых данных
    modalBody.appendChild(form);

    // Показываем модальное окно
    $('#editEmployeeModal').modal('show');
}

// Функция для отправки PUT-запроса на сервер
async function updateEmployee(updatedEmployee) {
    try {
        const response = await fetch('http://localhost:8080/employee/edit', {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(updatedEmployee)
        });
        if (!response.ok) {
            throw new Error('Ошибка при сохранении изменений');
        }
        console.log('Изменения успешно сохранены');
        $('#editEmployeeModal').modal('hide'); // Закрываем модальное окно после успешного сохранения
    } catch (error) {
        console.error('Ошибка:', error);
    }
}

// Функция для обработки отправки формы
async function handleFormSubmit(event) {
    event.preventDefault(); // Отменяем стандартное поведение отправки формы

    // Получаем данные формы
    const formData = new FormData(event.target);

    // Преобразуем данные формы в объект
    const updatedEmployee = {};
    formData.forEach((value, key) => {
        updatedEmployee[key] = value;
    });

    // Вызываем функцию для отправки PUT-запроса на сервер
    await updateEmployee(updatedEmployee);
}

// Добавляем обработчик события отправки формы редактирования сотрудника
document.getElementById('editEmployeeForm').addEventListener('submit', handleFormSubmit);


// Функция для создания иконки карандаша и добавления обработчика события на клик
function createEditIcon(id) {
    const icon = document.createElement('i');
    icon.classList.add('fas', 'fa-pencil-alt');
    icon.setAttribute('title', 'Редактировать');
    icon.addEventListener('click', function() {
        handleEditClick(id);
    });
    return icon;
}

// Функция для отображения списка сотрудников в таблице
function displayEmployees(employees) {
    const tableBody = document.getElementById('employeesTableBody');
    tableBody.innerHTML = ''; // Очищаем содержимое таблицы перед добавлением новых данных
    employees.forEach(employee => {
        const row = document.createElement('tr');
        row.innerHTML = `
      <td>${employee.name}</td>
      <td>${employee.lastName}</td>
      <td>${employee.salary}</td>
      <td>${employee.citizenship}</td>
      <td>${employee.department.name}</td>
      <td></td>
    `;
        const editIcon = createEditIcon(employee.id);
        row.querySelector('td:last-child').appendChild(editIcon);
        tableBody.appendChild(row);
    });
}

// Функция для отправки PUT-запроса на сервер
async function updateEmployee(updatedEmployee) {
    try {
        const response = await fetch('http://localhost:8080/employee/edit', {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(updatedEmployee)
        });
        if (!response.ok) {
            throw new Error('Ошибка при сохранении изменений');
        }
        console.log('Изменения успешно сохранены');
        $('#editEmployeeModal').modal('hide'); // Закрываем модальное окно после успешного сохранения
    } catch (error) {
        console.error('Ошибка:', error);
    }
}

// Функция для обработки отправки формы
async function handleFormSubmit(event) {
    event.preventDefault(); // Отменяем стандартное поведение отправки формы

    // Получаем данные формы
    const formData = new FormData(event.target);

    // Преобразуем данные формы в объект
    const updatedEmployee = {};
    formData.forEach((value, key) => {
        updatedEmployee[key] = value;
    });

    // Вызываем функцию для отправки PUT-запроса на сервер
    await updateEmployee(updatedEmployee);
}

// Добавляем обработчик события отправки формы редактирования сотрудника
document.getElementById('editEmployeeForm').addEventListener('submit', handleFormSubmit);

// Добавляем обработчик события отправки формы редактирования сотрудника
document.getElementById('editEmployeeForm').addEventListener('submit', function(event) {
    event.preventDefault();
    // Здесь нужно выполнить запрос на сервер для сохранения изменений
    // После успешного сохранения закрываем модальное окно
    $('#editEmployeeModal').modal('hide');
});

// Функция для создания иконки удаления и добавления обработчика события на клик
function createDeleteIcon(id) {
    const icon = document.createElement('i');
    icon.classList.add('fas', 'fa-trash-alt');
    icon.setAttribute('title', 'Удалить');
    icon.addEventListener('click', function() {
        handleDeleteClick(id);
    });
    return icon;
}

// Функция для обработки клика по иконке удаления сотрудника
function handleDeleteClick(id) {
    // Здесь нужно выполнить запрос на сервер для удаления сотрудника с указанным id
    // После успешного удаления обновляем список сотрудников
    console.log(`Удалить сотрудника с ID ${id}`);
    // Здесь может быть код для удаления сотрудника
    // После удаления сотрудника обновляем таблицу
}

// Функция для отправки DELETE-запроса на сервер для удаления сотрудника
async function deleteEmployee(id) {
    try {
        const response = await fetch(`http://localhost:8080/employee/delete-employee/${id}`, {
            method: 'DELETE'
        });
        if (!response.ok) {
            throw new Error('Ошибка при удалении сотрудника');
        }
        console.log(`Сотрудник с ID ${id} успешно удален`);
        // Здесь можно добавить обновление таблицы после успешного удаления сотрудника
    } catch (error) {
        console.error('Ошибка:', error);
    }
}

// Функция для обработки клика по иконке удаления сотрудника
function handleDeleteClick(id) {
    // Здесь вызываем функцию для отправки DELETE-запроса на сервер для удаления сотрудника
    deleteEmployee(id);
}

// Функция для создания иконки удаления и добавления обработчика события на клик
function createDeleteIcon(id) {
    const icon = document.createElement('i');
    icon.classList.add('fas', 'fa-trash-alt');
    icon.setAttribute('title', 'Удалить');
    icon.addEventListener('click', function() {
        handleDeleteClick(id);
    });
    return icon;
}

// Функция для отображения списка сотрудников в таблице
function displayEmployees(employees) {
    const tableBody = document.getElementById('employeesTableBody');
    tableBody.innerHTML = ''; // Очищаем содержимое таблицы перед добавлением новых данных
    employees.forEach(employee => {
        const row = document.createElement('tr');
        row.innerHTML = `
      <td>${employee.name}</td>
      <td>${employee.lastName}</td>
      <td>${employee.salary}</td>
      <td>${employee.citizenship}</td>
      <td>${employee.department.name}</td>
      <td></td>
      <td></td>
    `;
        const editIcon = createEditIcon(employee.id);
        const deleteIcon = createDeleteIcon(employee.id);
        row.querySelector('td:nth-child(6)').appendChild(editIcon); // Колонка "Действия"
        row.querySelector('td:nth-child(7)').appendChild(deleteIcon); // Колонка "Действия"
        tableBody.appendChild(row);
    });
}


// Вызываем функцию для загрузки и отображения сотрудников при загрузке страницы
fetchEmployees();
