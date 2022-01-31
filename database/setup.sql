
CREATE DATABASE IF NOT EXISTS `eshopping` DEFAULT CHARACTER SET utf16 COLLATE utf16_bin;
USE `eshopping`;

-- --------------------------------------------------------

--
-- Table structure for table `address`
--

CREATE TABLE `address` (
  `id` bigint(20) NOT NULL,
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `address_line1` varchar(255) COLLATE utf16_bin DEFAULT NULL,
  `address_line2` varchar(255) COLLATE utf16_bin DEFAULT NULL,
  `address_line3` varchar(255) COLLATE utf16_bin DEFAULT NULL,
  `country` varchar(255) COLLATE utf16_bin DEFAULT NULL,
  `phone_number` varchar(255) COLLATE utf16_bin DEFAULT NULL,
  `pobox` varchar(255) COLLATE utf16_bin DEFAULT NULL,
  `postal_code` varchar(255) COLLATE utf16_bin DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf16 COLLATE=utf16_bin;

--
-- Dumping data for table `address`
--

INSERT INTO `address` (`id`, `created_date`, `updated_date`, `address_line1`, `address_line2`, `address_line3`, `country`, `phone_number`, `pobox`, `postal_code`) VALUES
(1, '2022-01-30 18:08:59', '2022-01-30 18:08:59', 'address line 1', 'address line 2', 'address line 3', 'Greek', '(+123)123-123123', '123', '123\r\n');

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE `admin` (
  `email` varchar(255) COLLATE utf16_bin DEFAULT NULL,
  `username` varchar(255) COLLATE utf16_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf16 COLLATE=utf16_bin;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`email`, `username`) VALUES
('admin@test.com', 'admin');

-- --------------------------------------------------------

--
-- Table structure for table `cart_item`
--

CREATE TABLE `cart_item` (
  `id` bigint(20) NOT NULL,
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `quantity` int(11) DEFAULT NULL,
  `product` bigint(20) DEFAULT NULL,
  `cart_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf16 COLLATE=utf16_bin;

--
-- Dumping data for table `cart_item`
--

INSERT INTO `cart_item` (`id`, `created_date`, `updated_date`, `quantity`, `product`, `cart_id`) VALUES
(1, '2022-01-29 17:23:30', '2022-01-29 17:23:30', 10, 1, 2),
(2, '2022-01-29 17:26:36', '2022-01-29 17:26:36', 10, 1, 2),
(4, '2022-01-30 22:46:33', '2022-01-30 23:02:55', 5, 1, NULL),
(5, '2022-01-30 22:49:55', '2022-01-30 22:49:55', 3, 2, NULL),
(6, '2022-01-31 01:21:23', '2022-01-31 01:23:29', 5, 1, NULL),
(7, '2022-01-31 02:06:28', '2022-01-31 02:06:28', 10, 1, 4);

-- --------------------------------------------------------

--
-- Table structure for table `customer`
--

CREATE TABLE `customer` (
  `payment_code` varchar(255) COLLATE utf16_bin DEFAULT NULL,
  `username` varchar(255) COLLATE utf16_bin NOT NULL,
  `address` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf16 COLLATE=utf16_bin;

--
-- Dumping data for table `customer`
--

INSERT INTO `customer` (`payment_code`, `username`, `address`) VALUES
(NULL, 'john', 1),
('test', 'nassar', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `news`
--

CREATE TABLE `news` (
  `id` bigint(20) NOT NULL,
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `description` varchar(255) COLLATE utf16_bin DEFAULT NULL,
  `title` varchar(255) COLLATE utf16_bin DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf16 COLLATE=utf16_bin;

--
-- Dumping data for table `news`
--

INSERT INTO `news` (`id`, `created_date`, `updated_date`, `description`, `title`) VALUES
(1, '2022-01-31 01:15:15', '2022-01-31 01:15:15', 'test news item', 'test news item'),
(2, '2022-01-31 03:41:42', '2022-01-31 03:44:38', NULL, '30% Sall on Jack Danil Hunny 750');

-- --------------------------------------------------------

--
-- Table structure for table `order_details`
--

CREATE TABLE `order_details` (
  `id` bigint(20) NOT NULL,
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `payment_code` varchar(255) COLLATE utf16_bin DEFAULT NULL,
  `status` varchar(255) COLLATE utf16_bin DEFAULT NULL,
  `total_price` double DEFAULT NULL,
  `customer` varchar(255) COLLATE utf16_bin DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf16 COLLATE=utf16_bin;

--
-- Dumping data for table `order_details`
--

INSERT INTO `order_details` (`id`, `created_date`, `updated_date`, `payment_code`, `status`, `total_price`, `customer`) VALUES
(1, '2022-01-30 01:19:39', '2022-01-30 01:19:39', NULL, 'CHECKOUT', 2000, 'nassar'),
(2, '2022-01-31 02:06:43', '2022-01-31 02:06:43', NULL, 'CHECKOUT', 1000, 'john');

-- --------------------------------------------------------

--
-- Table structure for table `order_item`
--

CREATE TABLE `order_item` (
  `id` bigint(20) NOT NULL,
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `quantity` int(11) DEFAULT NULL,
  `product` bigint(20) DEFAULT NULL,
  `order_details_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf16 COLLATE=utf16_bin;

--
-- Dumping data for table `order_item`
--

INSERT INTO `order_item` (`id`, `created_date`, `updated_date`, `quantity`, `product`, `order_details_id`) VALUES
(1, '2022-01-30 01:19:39', '2022-01-30 01:19:39', 10, 1, 1),
(2, '2022-01-31 02:06:43', '2022-01-31 02:06:43', 10, 1, 2);

-- --------------------------------------------------------

--
-- Table structure for table `product`
--

CREATE TABLE `product` (
  `id` bigint(20) NOT NULL,
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `description` varchar(255) COLLATE utf16_bin DEFAULT NULL,
  `name` varchar(255) COLLATE utf16_bin DEFAULT NULL,
  `price` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf16 COLLATE=utf16_bin;

--
-- Dumping data for table `product`
--

INSERT INTO `product` (`id`, `created_date`, `updated_date`, `description`, `name`, `price`) VALUES
(1, '2022-01-29 17:21:49', '2022-01-29 17:21:49', NULL, 'test1', 100),
(2, '2022-01-29 17:26:01', '2022-01-29 17:26:01', NULL, 'test2', 10),
(3, '2022-01-30 19:28:33', '2022-01-30 19:28:33', NULL, 'test2', 10),
(4, '2022-01-31 03:29:34', '2022-01-31 03:36:23', 'From Bethlahem', 'Red Wine since 1988', 200);

-- --------------------------------------------------------

--
-- Table structure for table `shopping_cart`
--

CREATE TABLE `shopping_cart` (
  `id` bigint(20) NOT NULL,
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `customer` varchar(255) COLLATE utf16_bin DEFAULT NULL,
  `total_price` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf16 COLLATE=utf16_bin;

--
-- Dumping data for table `shopping_cart`
--

INSERT INTO `shopping_cart` (`id`, `created_date`, `updated_date`, `customer`, `total_price`) VALUES
(2, '2022-01-29 05:05:01', '2022-01-29 17:26:36', 'nassar', 2000),
(4, '2022-01-30 22:42:25', '2022-01-30 22:49:56', 'john', 1030);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `username` varchar(255) COLLATE utf16_bin NOT NULL,
  `password` varchar(255) COLLATE utf16_bin DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf16 COLLATE=utf16_bin;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`username`, `password`) VALUES
('admin', '$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6'),
('john', '$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6'),
('nassar', '$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `address`
--
ALTER TABLE `address`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`username`);

--
-- Indexes for table `cart_item`
--
ALTER TABLE `cart_item`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK1995n4vu1kx536ajess0mhn60` (`product`),
  ADD KEY `FKf9e1brwb4ea9cxtvxbs0wugdt` (`cart_id`);

--
-- Indexes for table `customer`
--
ALTER TABLE `customer`
  ADD PRIMARY KEY (`username`),
  ADD KEY `FKdcvj0ju58s9csxkii6tusumdl` (`address`);

--
-- Indexes for table `news`
--
ALTER TABLE `news`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `order_details`
--
ALTER TABLE `order_details`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK8ixa8rwt0koyn7a5ijyk0m90o` (`customer`);

--
-- Indexes for table `order_item`
--
ALTER TABLE `order_item`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKmulv4gttli0eov5xuhm7v9td7` (`product`),
  ADD KEY `FKroqxg3b97n259t79co1tol4an` (`order_details_id`);

--
-- Indexes for table `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `shopping_cart`
--
ALTER TABLE `shopping_cart`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK9yov2kkvkguw19gcqb8uxc2u0` (`customer`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`username`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `address`
--
ALTER TABLE `address`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `cart_item`
--
ALTER TABLE `cart_item`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `news`
--
ALTER TABLE `news`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `order_details`
--
ALTER TABLE `order_details`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `order_item`
--
ALTER TABLE `order_item`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `product`
--
ALTER TABLE `product`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `shopping_cart`
--
ALTER TABLE `shopping_cart`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `admin`
--
ALTER TABLE `admin`
  ADD CONSTRAINT `FKct0xnk0e2pv9jotrkeqy09xde` FOREIGN KEY (`username`) REFERENCES `user` (`username`);

--
-- Constraints for table `cart_item`
--
ALTER TABLE `cart_item`
  ADD CONSTRAINT `FK1995n4vu1kx536ajess0mhn60` FOREIGN KEY (`product`) REFERENCES `product` (`id`),
  ADD CONSTRAINT `FKf9e1brwb4ea9cxtvxbs0wugdt` FOREIGN KEY (`cart_id`) REFERENCES `shopping_cart` (`id`);

--
-- Constraints for table `customer`
--
ALTER TABLE `customer`
  ADD CONSTRAINT `FK418xwcu9qhc1ttktrshmrv952` FOREIGN KEY (`username`) REFERENCES `user` (`username`),
  ADD CONSTRAINT `FKdcvj0ju58s9csxkii6tusumdl` FOREIGN KEY (`address`) REFERENCES `address` (`id`);

--
-- Constraints for table `order_details`
--
ALTER TABLE `order_details`
  ADD CONSTRAINT `FK8ixa8rwt0koyn7a5ijyk0m90o` FOREIGN KEY (`customer`) REFERENCES `customer` (`username`);

--
-- Constraints for table `order_item`
--
ALTER TABLE `order_item`
  ADD CONSTRAINT `FKmulv4gttli0eov5xuhm7v9td7` FOREIGN KEY (`product`) REFERENCES `product` (`id`),
  ADD CONSTRAINT `FKroqxg3b97n259t79co1tol4an` FOREIGN KEY (`order_details_id`) REFERENCES `order_details` (`id`);

--
-- Constraints for table `shopping_cart`
--
ALTER TABLE `shopping_cart`
  ADD CONSTRAINT `FK9yov2kkvkguw19gcqb8uxc2u0` FOREIGN KEY (`customer`) REFERENCES `customer` (`username`);
COMMIT;
