1. Добавить методы сортировок:
- сортировка по возрасту
- сортировка по зарплате
- вычисление средней зп по отделу 

2. Добавить функции:
- прием на работу нового сотрудника (если отдел пустой, то дефолтный)
- изменение отдела
- расчет зп за год 
- расчет индивидуальных надбавок, премий

3. Изменение сущностей: 
- добавление сотруднику признака должность с валидацией (руководитель может быть только один)
- добавление адреса компании с связью один-к-одному
- добавление сущности Task (задача) 
(id, creator, executor, status, parent, deadline)
- **добавление сущностей User и Role со связью Many-to-Many**

4. Инфра
- **MAPSTRUCT**
- добавление валидатора (придумать какой, можно **implements Validator**)
- добавление GlobalExceptionHandler (@ControllerAdvice + @ExceptionHandler(Exception.class))
- return new ResponseEntity<>(obj, HttpStatus.***);
- **настройка аутентификации через Spring Security
- добавление уникальности полю username
- добавление типа для поля username
- добавление Aspects**


// streams + Collections + workflow 