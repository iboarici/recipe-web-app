/*
drop table if exists public.INGREDIENTS;
drop table if exists public.COOKING_INSTRUCTIONS;
drop table if exists public.FAVORITE_RECIPES;
drop table if exists public.RECIPES;
drop table if exists public.USERS;
drop table if exists public.CATEGORIES;

 */

create table IF NOT EXISTS public.USERS
(
    USERNAME VARCHAR,
    PASSWORD VARCHAR,
    ROLE VARCHAR,
    PRIMARY KEY (username)
);

CREATE SEQUENCE IF NOT EXISTS  CATEGORIES_SERIAL START 6;


create table IF NOT EXISTS public.CATEGORIES
(
    ID INT PRIMARY KEY DEFAULT  nextval('CATEGORIES_SERIAL'),
    NAME VARCHAR(100) NOT NULL
);

CREATE SEQUENCE IF NOT EXISTS  RECIPES_SERIAL START 8;

create table IF NOT EXISTS public.RECIPES
(
    ID   INT PRIMARY KEY DEFAULT  nextval('RECIPES_SERIAL'),
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
    FOREIGN KEY (CATEGORY_ID) references public.CATEGORIES (ID)
);

create table IF NOT EXISTS public.FAVORITE_RECIPES
(
    RECIPE_ID int,
    USERNAME VARCHAR,
    FOREIGN KEY (RECIPE_ID) references public.RECIPES (ID),
    FOREIGN KEY (USERNAME) references public.USERS (username)
);

CREATE SEQUENCE IF NOT EXISTS  INGREDIENTS_SERIAL START 58;
create table IF NOT EXISTS public.INGREDIENTS
(
    ID INT PRIMARY KEY DEFAULT  nextval('INGREDIENTS_SERIAL'),
    RECIPE_ID int,
    NAME      VARCHAR,
    QUANTITY  FLOAT,
    UNIT      VARCHAR(100),
    SIZE      VARCHAR(100),
    ADDITONAL_INFO VARCHAR,
    FOREIGN KEY (RECIPE_ID) references public.RECIPES (ID)
);

CREATE SEQUENCE IF NOT EXISTS  COOKING_INSTRUCTIONS_SERIAL START 46;

create table IF NOT EXISTS public.COOKING_INSTRUCTIONS
(
    ID INT PRIMARY KEY DEFAULT  nextval('COOKING_INSTRUCTIONS_SERIAL'),
    RECIPE_ID   int,
    ORDER_ID    int not null,
    DESCRIPTION VARCHAR,
    IMAGE_URL   VARCHAR,
    VIDEO_URL   VARCHAR,
    FOREIGN KEY (RECIPE_ID) references public.RECIPES (ID)
);