BionicCreditSystem
==================
Use Case: 
1) Клиент
Просматривать профиль, незначительные изменения, просматривать/выбирать кредитные программы, просматривать заявку, делать оплату;	


2) Кредитный менеджер
Профиля пользователей ( добавление, удаление, редактирование), список заявок( подтверждение или отказ), список кредитов; 

3) Администратор
Конфигурация, редактирование, удаление, дабавление кредитных программ, добавление менеджеров;

Классы:
User, CreditProgram, Order/Credit;

class CreditProgram:
Fields: id, name, short, full, percent, status, termin, sum;

class User:
Fields: id, FIO, PNN, adress, age, income, login, password, role, e-mail;

class Order/Credit:
Fields: id_client, id_creditProgram, date, time, order_status, credit_status;
