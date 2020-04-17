CREATE TABLE `users`
(
    `id`       bigint(20) PRIMARY KEY AUTO_INCREMENT,
    `username` varchar(255) NOT NULL,
    `password` varchar(255) NOT NULL,
    `name`     varchar(255) NOT NULL,
    `email`    varchar(255) NOT NULL
);

CREATE TABLE `user_role`
(
    `user_id` bigint(20)   NOT NULL,
    `role`    varchar(255) NOT NULL,
    FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
);

CREATE TABLE `languages`
(
    `id`   int PRIMARY KEY AUTO_INCREMENT,
    `name` varchar(255) NOT NULL
);

CREATE TABLE `compilations`
(
    `id`    int PRIMARY KEY AUTO_INCREMENT,
    `title` varchar(255) NOT NULL,
    FULLTEXT KEY `title` (`title`)
);

CREATE TABLE `machines`
(
    `id`        bigint(20) PRIMARY KEY AUTO_INCREMENT,
    `code`      varchar(255) UNIQUE NOT NULL,
    `title`     varchar(255)        NOT NULL,
    `unit`      varchar(255)        NOT NULL,
    `unit_cost` double              NOT NULL
);

CREATE TABLE `materials`
(
    `id`          bigint(20) PRIMARY KEY AUTO_INCREMENT,
    `code`        varchar(255) UNIQUE NOT NULL,
    `unit`        varchar(255)        NOT NULL,
    `unit_cost`   double              NOT NULL,
    `unit_volume` double              NOT NULL
);

CREATE TABLE `materials_compilations`
(
    `material_id`    bigint(20) NOT NULL,
    `compilation_id` int        NOT NULL,
    FOREIGN KEY (`material_id`) REFERENCES `materials` (`id`),
    FOREIGN KEY (`compilation_id`) REFERENCES `compilations` (`id`)
);

CREATE TABLE `resources`
(
    `id`             bigint(20) PRIMARY KEY AUTO_INCREMENT,
    `code`           varchar(255) UNIQUE NOT NULL,
    `title`          varchar(255)        NOT NULL,
    `unit`           varchar(255)        NOT NULL,
    `unit_cost`      double              NOT NULL,
    `compilation_id` int                 NOT NULL,
    FOREIGN KEY (`compilation_id`) REFERENCES `compilations` (`id`)
);

CREATE TABLE `resource_measures`
(
    `id`          bigint(20) PRIMARY KEY AUTO_INCREMENT,
    `unit`        varchar(255) NOT NULL,
    `from`        double       NOT NULL,
    `to`          double       NOT NULL,
    `resource_id` bigint(20)   NOT NULL,
    FOREIGN KEY (`resource_id`) REFERENCES `resources` (`id`)
);

CREATE TABLE `work_scope`
(
    `id`    bigint(20) PRIMARY KEY AUTO_INCREMENT,
    `title` varchar(255) NOT NULL
);

CREATE TABLE `workforces`
(
    `id`        bigint(20) PRIMARY KEY AUTO_INCREMENT,
    `code`      varchar(255) UNIQUE NOT NULL,
    `title`     varchar(255)        NOT NULL,
    `unit`      varchar(255)        NOT NULL,
    `unit_cost` double              NOT NULL
);
