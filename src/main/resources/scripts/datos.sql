-- Limpieza opcional para re-ejecutar el script
DELETE FROM prestamos;
DELETE FROM cuentas;
DELETE FROM clientes;

------------------------------------------------------------
-- CLIENTES
------------------------------------------------------------
INSERT INTO clientes (id, numero) VALUES
  (1, '00103228'),
  (2, '70099925'),
  (3, '00298185'),
  (4, '15000125');

------------------------------------------------------------
-- CUENTAS DE DÉBITO (Saldo disponible por cliente)
-- Estado: ACTIVA | BLOQUEADA | CANCELADA
------------------------------------------------------------
INSERT INTO cuentas (id, cliente_id, monto, estado) VALUES
  (1, 1, 15375.28, 'ACTIVA'),      -- 00103228
  (2, 2, 3728.51,  'BLOQUEADA'),   -- 70099925
  (3, 3, 0.00,     'CANCELADA'),   -- 00298185
  (4, 4, 235.28,   'ACTIVA');      -- 15000125

------------------------------------------------------------
-- PRÉSTAMOS
-- Estado: PENDIENTE | PAGADO
-- Fecha como TIMESTAMP (H2 acepta 'YYYY-MM-DDTHH:MM:SS')
------------------------------------------------------------
-- Cliente 00103228 (id cliente = 1)
INSERT INTO prestamos (id, cliente_id, fecha, monto, estado) VALUES
  (1, 1, TIMESTAMP '2021-01-10 00:00:00', 37500.00, 'PENDIENTE'),
  (2, 1, TIMESTAMP '2021-01-19 00:00:00',   725.18, 'PENDIENTE'),
  (3, 1, TIMESTAMP '2021-01-31 00:00:00',  1578.22, 'PENDIENTE'),
  (4, 1, TIMESTAMP '2021-02-04 00:00:00',   380.00, 'PENDIENTE');

-- Cliente 70099925 (id cliente = 2)
INSERT INTO prestamos (id, cliente_id, fecha, monto, estado) VALUES
  (5, 2, TIMESTAMP '2021-01-07 00:00:00',  2175.25, 'PAGADO'),
  (6, 2, TIMESTAMP '2021-01-13 00:00:00',   499.99, 'PAGADO'),
  (7, 2, TIMESTAMP '2021-01-24 00:00:00',  5725.18, 'PENDIENTE'),
  (8, 2, TIMESTAMP '2021-02-07 00:00:00',   876.13, 'PENDIENTE');

-- Cliente 00298185 (id cliente = 3)
INSERT INTO prestamos (id, cliente_id, fecha, monto, estado) VALUES
  (9, 3, TIMESTAMP '2021-02-04 00:00:00',   545.55, 'PENDIENTE');

-- Cliente 15000125 (id cliente = 4)
INSERT INTO prestamos (id, cliente_id, fecha, monto, estado) VALUES
  (10, 4, TIMESTAMP '2020-12-31 00:00:00', 15220.00, 'PAGADO');
