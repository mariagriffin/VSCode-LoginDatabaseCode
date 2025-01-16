SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";

--
-- Database: `mysecureapplication`
--

DROP DATABASE IF EXISTS `mysecureapplication`;

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `mysecureapplication` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `mysecureapplication`;
-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `username` varchar(25) NOT NULL,
  `password` varchar(25) NOT NULL,
  `department` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`username`, `password`, `department`) VALUES
('adam.apple', 'password', 'business'),
('dan.doughnut', 'password', 'business'),
('john.jones', 'password', 'computing'),
('mary.murphy', 'password', 'computing'),
('ray.royal', 'password', 'business'),
('sam.son', 'password', 'computing'),
('tom.tank', 'password', 'computing');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD UNIQUE KEY `username` (`username`);
COMMIT;
