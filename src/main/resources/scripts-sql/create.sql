create sequence hibernate_sequence start with 1 increment by 1;
create table T_CLIENTE (id bigint not null, apellido varchar(50) not null, Provincia varchar(50) not null, calle varchar(50) not null, ciudad varchar(50) not null, codigoPostal varchar(50) not null, departamento varchar(50) not null, numeroCivico varchar(50) not null, piso varchar(50) not null, email varchar(255), nombre varchar(50) not null, telefono varchar(14), primary key (id));
create table T_CUENTABANCARIA (id bigint not null, descubiertoAcortado double not null, fechaCierre date, fechaCreacion date not null, nroCuenta bigint not null, saldoActual double not null, saldoInicial double not null, titular_id bigint not null, primary key (id));
create table T_CUENTABANCARIA_MONEDAEXTRANJERA (monedaAsociada char(255) not null, id bigint not null, primary key (id));
create table T_CUENTABANCARIA_MONEDANACIONAL (id bigint not null, primary key (id));
create table T_CUENTABANCARIA_R_COTITULAR (cuentasCotitular_id bigint not null, cotitulares_id bigint not null, primary key (cuentasCotitular_id, cotitulares_id));
create table T_CUENTABANCARIA_R_MOVIMIENTO (T_CUENTABANCARIA_id bigint not null, movimientosRealizados_id bigint not null, primary key (T_CUENTABANCARIA_id, movimientosRealizados_id));
create table T_MOVIMIENTO (id bigint not null, descripcion varchar(255) not null, fechaHora timestamp not null, monto double not null, primary key (id));
create table T_MOVIMIENTO_COMPRA_MONEDAEXTRANJERA (comisionAplicada double not null, cotizacion double not null, id bigint not null, primary key (id));
create table T_MOVIMIENTO_DEPOSITO (cajero varchar(255) not null, id bigint not null, primary key (id));
create table T_MOVIMIENTO_EXTRACCION (cajero varchar(255) not null, id bigint not null, primary key (id));
create table T_MOVIMIENTO_TRANSFERENCIA_REALIZADA (id bigint not null, cuentaDestino_id bigint not null, primary key (id));
create table T_MOVIMIENTO_TRANSFERENCIA_RECIBIDA (id bigint not null, cuentaOrigen_id bigint not null, primary key (id));
create table T_MOVIMIENTO_VENTA_MONEDAEXTRANJERA (comisionAplicada double not null, cotizacion double not null, id bigint not null, primary key (id));
alter table T_CUENTABANCARIA add constraint UK_1nadaidsfuvru6kukkvhpqo57 unique (nroCuenta);
alter table T_CUENTABANCARIA_R_MOVIMIENTO add constraint UK_mc7sgm40orev4c0qifbkqtwkn unique (movimientosRealizados_id);
alter table T_CUENTABANCARIA add constraint FKl4x0rgy9hqulhh7xsp60q3k4t foreign key (titular_id) references T_CLIENTE;
alter table T_CUENTABANCARIA_MONEDAEXTRANJERA add constraint FKcn0mwjrct105lmmm455dci21y foreign key (id) references T_CUENTABANCARIA;
alter table T_CUENTABANCARIA_MONEDANACIONAL add constraint FKp3ex23wug8guk4fk1qiqa2n2o foreign key (id) references T_CUENTABANCARIA;
alter table T_CUENTABANCARIA_R_COTITULAR add constraint FKeao5ej06odr2gs5t40vgceusi foreign key (cotitulares_id) references T_CLIENTE;
alter table T_CUENTABANCARIA_R_COTITULAR add constraint FK5vahouv15yi402l2i46crjhn7 foreign key (cuentasCotitular_id) references T_CUENTABANCARIA;
alter table T_CUENTABANCARIA_R_MOVIMIENTO add constraint FKaew3bua5agt2oxns8we2hrnde foreign key (movimientosRealizados_id) references T_MOVIMIENTO;
alter table T_CUENTABANCARIA_R_MOVIMIENTO add constraint FKb4t5tdyobpnttaog9gi2n0fii foreign key (T_CUENTABANCARIA_id) references T_CUENTABANCARIA;
alter table T_MOVIMIENTO_COMPRA_MONEDAEXTRANJERA add constraint FKe483lt185muahf9o7f2tkjqgg foreign key (id) references T_MOVIMIENTO;
alter table T_MOVIMIENTO_DEPOSITO add constraint FKqxlhy78dfp09phe11as0pecm3 foreign key (id) references T_MOVIMIENTO;
alter table T_MOVIMIENTO_EXTRACCION add constraint FKiv81hwg0608uptfhkf1r4hara foreign key (id) references T_MOVIMIENTO;
alter table T_MOVIMIENTO_TRANSFERENCIA_REALIZADA add constraint FKfevuecvyhcfhackyf0sadalg0 foreign key (cuentaDestino_id) references T_CUENTABANCARIA;
alter table T_MOVIMIENTO_TRANSFERENCIA_REALIZADA add constraint FKluysqyjukpa20bj55thyc6rpn foreign key (id) references T_MOVIMIENTO;
alter table T_MOVIMIENTO_TRANSFERENCIA_RECIBIDA add constraint FKbc8nmg37v8fv291kak5n4ds8h foreign key (cuentaOrigen_id) references T_CUENTABANCARIA;
alter table T_MOVIMIENTO_TRANSFERENCIA_RECIBIDA add constraint FKlfgkaxxspol9y2cptmbib26ba foreign key (id) references T_MOVIMIENTO;
alter table T_MOVIMIENTO_VENTA_MONEDAEXTRANJERA add constraint FKj93ok917396hcxdxmd8mq3jcr foreign key (id) references T_MOVIMIENTO;
