drop table if exists public.USERS;
drop table if exists public.CATEGORIES;
drop table if exists public.RECIPES;
drop table if exists public.RECIPE_CATEGORIES;
drop table if exists public.INGREDIENTS;
drop table if exists public.COOKING_INSTRUCTIONS;
drop table if exists public.FAVORITE_RECIPES;

create table IF NOT EXISTS public.USERS
(
    USERNAME VARCHAR,
    PASSWORD VARCHAR,
    ROLE VARCHAR,
    PRIMARY KEY (username)
);

create table IF NOT EXISTS public.CATEGORIES
(
    ID   INTEGER      NOT NULL AUTO_INCREMENT,
    NAME VARCHAR(100) NOT NULL,
    PRIMARY KEY (id)
);

create table IF NOT EXISTS public.RECIPES
(
    ID                    int          not null AUTO_INCREMENT,
    CATEGORY_ID           INTEGER NOT NULL,
    TITLE                 varchar(500) not null,
    CREATE_DATE           TIMESTAMP,
    VEGETARIAN            boolean,
    SUITABLE_PEOPLE_COUNT int,
    PREPARE_TIME_IN_MINUTES int,
    COOKING_TIME_IN_MINUTES int,
    CALORIES                int,
    IMAGE_URL             VARCHAR,
    VIDEO_URL             VARCHAR,
    CREATED_USER          VARCHAR,
    PRIMARY KEY (ID),
    FOREIGN KEY (CATEGORY_ID) references public.CATEGORIES (ID)
);

create table IF NOT EXISTS public.FAVORITE_RECIPES
(
    RECIPE_ID int,
    USERNAME VARCHAR,
    FOREIGN KEY (RECIPE_ID) references public.RECIPES (ID),
    FOREIGN KEY (USERNAME) references public.USERS (username)
);


create table IF NOT EXISTS public.INGREDIENTS
(
    ID        int not null AUTO_INCREMENT,
    RECIPE_ID int,
    NAME      VARCHAR,
    QUANTITY  FLOAT,
    UNIT      VARCHAR(100),
    SIZE      VARCHAR(100),
    ADDITONAL_INFO VARCHAR,
    PRIMARY KEY (ID),
    FOREIGN KEY (RECIPE_ID) references public.RECIPES (ID)
);

create table IF NOT EXISTS public.COOKING_INSTRUCTIONS
(
    ID          int not null AUTO_INCREMENT,
    RECIPE_ID   int,
    ORDER_ID    int not null,
    DESCRIPTION VARCHAR,
    IMAGE_URL   VARCHAR,
    VIDEO_URL   VARCHAR,
    PRIMARY KEY (ID),
    FOREIGN KEY (RECIPE_ID) references public.RECIPES (ID)
);