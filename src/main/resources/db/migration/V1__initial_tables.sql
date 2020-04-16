CREATE TABLE `users`
(
    `id`       bigint(20) NOT NULL AUTO_INCREMENT,
    `email`    varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
    `name`     varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
    `password` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
    `username` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COLLATE = utf8_unicode_ci;

CREATE TABLE `user_role`
(
    `user_id` bigint(20) NOT NULL,
    `roles`   varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
    FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COLLATE = utf8_unicode_ci;

CREATE TABLE `languages`
(
    `id`   bigint(20) NOT NULL AUTO_INCREMENT,
    `name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COLLATE = utf8_unicode_ci;

CREATE TABLE `compilations`
(
    `id`    bigint(20) NOT NULL AUTO_INCREMENT,
    `title` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
    PRIMARY KEY (`id`),
    FULLTEXT KEY `title` (`title`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COLLATE = utf8_unicode_ci;

CREATE TABLE `machines`
(
    `id`        bigint(20) NOT NULL AUTO_INCREMENT,
    `code`      varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
    `title`     varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
    `unit`      varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
    `unit_cost` double     NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY (`code`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COLLATE = utf8_unicode_ci;

CREATE TABLE `materials`
(
    `id`          bigint(20) NOT NULL AUTO_INCREMENT,
    `code`        varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
    `unit`        varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
    `unit_cost`   double     NOT NULL,
    `unit_volume` double     NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `UK_hi5ut2xo58x4o7dc24esv9r24` (`code`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COLLATE = utf8_unicode_ci;

CREATE TABLE `materials_compilations`
(
    `material_id`    bigint(20) NOT NULL,
    `compilation_id` bigint(20) NOT NULL,
    FOREIGN KEY (`material_id`) REFERENCES `materials` (`id`),
    FOREIGN KEY (`compilation_id`) REFERENCES `compilations` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COLLATE = utf8_unicode_ci;

CREATE TABLE `resources`
(
    `id`             bigint(20)                           NOT NULL AUTO_INCREMENT,
    `code`           varchar(255) COLLATE utf8_unicode_ci NOT NULL,
    `title`          varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
    `unit`           varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
    `unit_cost`      double                               NOT NULL,
    `compilation_id` bigint(20)                           DEFAULT NULL,
    PRIMARY KEY (`id`),
    FOREIGN KEY (`compilation_id`) REFERENCES `compilations` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COLLATE = utf8_unicode_ci;

CREATE TABLE `work_scope`
(
    `id`    bigint(20) NOT NULL AUTO_INCREMENT,
    `title` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COLLATE = utf8_unicode_ci;

CREATE TABLE `workforces`
(
    `id`        bigint(20) NOT NULL AUTO_INCREMENT,
    `code`      varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
    `title`     varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
    `unit`      varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
    `unit_cost` double     NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE (`code`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COLLATE = utf8_unicode_ci;
