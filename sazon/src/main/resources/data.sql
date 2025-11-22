INSERT INTO Categoria (Nombre, Descripcion, Condicion)
VALUES
    ('Entradas', 'Platos ligeros para comenzar la comida', 1),        -- ID 1
    ('Platos de Fondo', 'Platos principales del restaurante', 1),     -- ID 2
    ('Postres', 'Dulces para finalizar la comida', 1),                -- ID 3
    ('Bebidas', 'Bebidas frías y calientes', 1),                      -- ID 4
    ('Promociones', 'Ofertas especiales del día', 1);                 -- ID 5

-- =============================================
-- PASO 3: INSERCIÓN DE PLATILLOS CON CATEGORÍA REASIGNADA
-- Los platillos originales que no caben en las 5 categorías han sido reasignados.
-- IDs asignados: 1, 2, 3, 4, 5, 6, 7, 8, 9, 10
-- =============================================
INSERT INTO Platillo (Nombre, Descripcion, Categoria, Precio, Condicion)
VALUES
    ('Causa Limeña', 'Entrada fría de papa amarilla con pollo', 1, 12.00, 1),
    ('Lomo Saltado', 'Clásico plato peruano con carne y papas fritas', 2, 25.50, 1),
    ('Suspiro Limeño', 'Postre tradicional con manjar blanco y merengue', 3, 10.00, 1),
    ('Chicha Morada', 'Bebida a base de maíz morado', 4, 5.00, 1),
    ('Combo Familiar', 'Promoción con varios platos a menor precio', 5, 40.00, 1),
    ('Sopa Criolla (Reasig.)', 'Sopa caliente con carne, leche y fideos', 2, 8.00, 1),
    ('Ensalada César (Reasig.)', 'Lechuga, pollo, queso y aderezo César', 1, 9.50, 1),
    ('Spaghetti Bolognesa (Reasig.)', 'Pasta con salsa de carne y tomate', 2, 16.00, 1),
    ('Pollo a la Brasa (Reasig.)', 'Pollo a la parrilla con papas', 2, 22.00, 1),
    ('Ceviche de Pescado (Reasig.)', 'Pescado fresco marinado en limón', 2, 18.50, 1);

-- =============================================
-- PASO 4: INSERCIÓN DE VENTAS (MISMOS IDs 1 al 17)
-- Se reemplaza 'Completado' por 'Entregado' en el campo Estado.
-- =============================================
INSERT INTO Venta (Fecha, MetodoPago, Tipo, Estado, NombreCliente, DireccionCliente, TelefonoCliente, Referencia)
VALUES
    ('2025-11-01 13:00:00', 'Efectivo', 'Local', 'Entregado', 'Carlos Mendoza', 'Av. El Sol 150', '999222333', 'Cerca del centro comercial'),
    ('2025-11-01 14:30:00', 'Tarjeta', 'Delivery', 'Entregado', 'Juan Pérez', 'Av. Los Olivos 345', '987654321', 'Frente al parque'),
    ('2025-11-02 12:45:00', 'Yape', 'Local', 'Entregado', 'Ana Torres', 'Jr. Pardo 890', '988776655', 'Frente a la panadería'),
    ('2025-11-02 15:00:00', 'Plin', 'Delivery', 'Entregado', 'María Torres', 'Jr. Lima 678', '912345678', 'Cerca al colegio San Martín'),
    ('2025-11-03 19:30:00', 'Efectivo', 'Local', 'Entregado', 'Javier López', 'Calle Pescadores 24', '977885544', 'Junto al restaurante La Mar'),
    ('2025-11-03 20:15:00', 'Tarjeta', 'Delivery', 'Pendiente', 'Carlos Ramos', 'Av. Grau 120', '998877665', 'Frente al banco BCP'),
    ('2025-11-04 11:20:00', 'Yape', 'Local', 'Entregado', 'Lucía Fernández', 'Av. Larco 456', '944123678', 'Cerca del parque principal'),
    ('2025-11-04 13:45:00', 'Efectivo', 'Delivery', 'Entregado', 'Ana Gutiérrez', 'Calle Los Sauces 45', '956123789', 'Edificio azul'),
    ('2025-11-05 18:10:00', 'Tarjeta', 'Local', 'Entregado', 'Carlos Sánchez', 'Av. La Marina 1500', '977123456', 'Cerca de la playa'),
    ('2025-11-05 20:30:00', 'Plin', 'Delivery', 'Pendiente', 'Luis Fernández', 'Jr. Cuzco 232', '999111222', 'Al costado del mercado'),
    ('2025-09-10 14:00:00', 'Efectivo', 'Local', 'Entregado', 'Sofia Reyes', 'Calle Sol 123', '955112233', 'Puerta verde'),
    ('2025-09-20 20:45:00', 'Yape', 'Delivery', 'Entregado', 'Marcos Vidal', 'Av. Luna 456', '944332211', 'Edificio gris'),
    ('2025-10-05 13:20:00', 'Tarjeta', 'Local', 'Entregado', 'Elena Ruiz', 'Jr. Unión 789', '933445566', 'Frente a la plaza'),
    ('2025-10-15 19:00:00', 'Plin', 'Delivery', 'Entregado', 'Felipe Castro', 'Calle Girasoles 10', '922110099', 'Casa de dos pisos'),
    ('2025-10-25 15:30:00', 'Efectivo', 'Local', 'Entregado', 'Gloria Salas', 'Av. Perú 20', '911998877', 'Al costado del banco'),
    ('2025-12-01 13:00:00', 'Tarjeta', 'Local', 'Entregado', 'Héctor Soto', 'Av. Alameda 333', '977665544', 'Cerca de la iglesia'),
    ('2025-12-10 21:00:00', 'Efectivo', 'Delivery', 'Entregado', 'Irene Vega', 'Jr. Arequipa 500', '966554433', 'Tercer piso');

-- =============================================
-- PASO 5: DETALLE VENTA CON IDs DE PLATILLOS AJUSTADOS
-- Los IDs 5, 6, 7, 8, 9, 10 ahora apuntan a los platillos reasignados.
-- =============================================
INSERT INTO DetalleVenta (Venta, Platillo, Cantidad)
VALUES
    (1, 1, 2),    -- Causa Limeña (Cat 1: Entradas)
    (1, 4, 2),    -- Chicha Morada (Cat 4: Bebidas)
    (2, 9, 1),    -- Pollo a la Brasa (Cat 2: Platos Fondo, antes 8)
    (2, 4, 2),    -- Chicha Morada (Cat 4: Bebidas)
    (3, 2, 1),    -- Lomo Saltado (Cat 2: Platos Fondo)
    (4, 10, 2),   -- Ceviche (Cat 2: Platos Fondo, antes 9)
    (5, 6, 1),    -- Sopa Criolla (Cat 2: Platos Fondo, antes 5)
    (6, 8, 1),    -- Spaghetti (Cat 2: Platos Fondo, antes 7)
    (7, 7, 3),    -- Ensalada César (Cat 1: Entradas, antes 6)
    (8, 5, 1),    -- Combo Familiar (Cat 5: Promociones, antes 10)
    (11, 2, 2),   -- Lomo Saltado (Cat 2: Platos Fondo)
    (11, 3, 1),   -- Suspiro Limeño (Cat 3: Postres)
    (12, 9, 1),   -- Pollo a la Brasa (Cat 2: Platos Fondo, antes 8)
    (12, 4, 3),   -- Chicha Morada (Cat 4: Bebidas)
    (13, 10, 1),  -- Ceviche (Cat 2: Platos Fondo, antes 9)
    (13, 1, 1),   -- Causa Limeña (Cat 1: Entradas)
    (14, 5, 1),   -- Combo Familiar (Cat 5: Promociones, antes 10)
    (15, 8, 2),   -- Spaghetti (Cat 2: Platos Fondo, antes 7)
    (15, 3, 1),   -- Suspiro Limeño (Cat 3: Postres)
    (16, 9, 2),   -- Pollo a la Brasa (Cat 2: Platos Fondo, antes 8)
    (16, 7, 1),   -- Ensalada César (Cat 1: Entradas, antes 6)
    (17, 2, 1),   -- Lomo Saltado (Cat 2: Platos Fondo)
    (17, 6, 2);   -- Sopa Criolla (Cat 2: Platos Fondo, antes 5)