CREATE TABLE `users`
(
    `id`       bigint(20) PRIMARY KEY AUTO_INCREMENT,
    `email`    varchar(255) NOT NULL UNIQUE,
    `name`     varchar(255) NOT NULL,
    `password` varchar(255) NOT NULL
);

CREATE TABLE `user_roles`
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
    `unit`      double              NOT NULL,
    `unit_cost` double              NOT NULL
);

CREATE TABLE `materials`
(
    `id`           bigint(20) PRIMARY KEY AUTO_INCREMENT,
    `code`         varchar(255) UNIQUE NOT NULL,
    `title`        varchar(255)        NOT NULL,
    `unit`         double              NOT NULL,
    `unit_cost`    double              NOT NULL,
    `measure_type` varchar(255)        NOT NULL
);

CREATE TABLE `resources`
(
    `id`             bigint(20) PRIMARY KEY AUTO_INCREMENT,
    `code`           varchar(255) UNIQUE NOT NULL,
    `title`          varchar(255)        NOT NULL,
    `unit`           varchar(255)        NOT NULL,
    `unit_cost`      double              NOT NULL,
    `measures`       json                NOT NULL,
    `compilation_id` int                 NOT NULL,
    FOREIGN KEY (`compilation_id`) REFERENCES `compilations` (`id`),
    FULLTEXT KEY `title` (`title`)
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
    `unit`      double              NOT NULL,
    `unit_cost` double              NOT NULL
);

CREATE TABLE `resource_machines`
(
    `resource_id` bigint(20) NOT NULL,
    `machine_id`  bigint(20) NOT NULL,
    FOREIGN KEY (`resource_id`) REFERENCES `resources` (`id`),
    FOREIGN KEY (`machine_id`) REFERENCES `machines` (`id`)
);

CREATE TABLE `resource_workforces`
(
    `resource_id`  bigint(20) NOT NULL,
    `workforce_id` bigint(20) NOT NULL,
    FOREIGN KEY (`resource_id`) REFERENCES `resources` (`id`),
    FOREIGN KEY (`workforce_id`) REFERENCES `workforces` (`id`)
);

CREATE TABLE `resource_materials`
(
    `resource_id` bigint(20) NOT NULL,
    `material_id` bigint(20) NOT NULL,
    FOREIGN KEY (`resource_id`) REFERENCES `resources` (`id`),
    FOREIGN KEY (`material_id`) REFERENCES `materials` (`id`)
);
