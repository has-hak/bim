TRUNCATE TABLE messages;

INSERT INTO messages(id, `values`)
VALUES ('ENG', '{
	"1": "English",
	"2": "Անգլերեն",
	"3": "Английский"
}'),
       ('ARM', '{
		   "1": "Armenian",
		   "2": "Հայերեն",
		   "3": "Армянский"
	   }'),
       ('RUS', '{
		   "1": "Russian",
		   "2": "Ռուսերեն",
		   "3": "Русский"
	   }'),
       ('compilations', '{
		   "1": "Compilations",
		   "2": "Ժողովածուներ",
		   "3": "Сборники"
	   }'),
       ('resources', '{
		   "1": "Resources",
		   "2": "Աշխատանքներ",
		   "3": "Работы"
	   }'),
       ('workforces', '{
		   "1": "Workforces",
		   "2": "Աշխատուժ",
		   "3": "Рабочая сила"
	   }'),
       ('machines', '{
		   "1": "Machines",
		   "2": "Մեքենաներ",
		   "3": "Машины"
	   }'),
       ('materials', '{
		   "1": "Materials",
		   "2": "Նյութեր",
		   "3": "Материалы"
	   }'),
       ('ui.sign-in', '{
		   "1": "Sign In",
		   "2": "Մուտք",
		   "3": "Вход"
	   }'),
       ('ui.sign-up', '{
		   "1": "Sign Up",
		   "2": "Գրանցվել",
		   "3": "Регистрация"
	   }'),
       ('ui.logout', '{
		   "1": "Logout",
		   "2": "Դուրս",
		   "3": "Выход"
	   }'),
       ('ui.main.data-view', '{
		   "1": "Data View",
		   "2": "Տվյալների դիտում",
		   "3": "Просмотр данных"
	   }'),
       ('ui.main.outlay-calculation', '{
		   "1": "Outlay Calculation",
		   "2": "Նախահաշվի կազմում",
		   "3": "Непонятно"
	   }'),
       ('ui.main.outlay-calculation.select-document', '{
		   "1": "Select document",
		   "2": "Ընտրեք ծավալաթերթը",
		   "3": "Выберите непонятный документ"
	   }'),
       ('ui.main.outlay-calculation.select-file', '{
		   "1": "Select File",
		   "2": "Ընտրեք ֆայլը",
		   "3": "Выберите файл"
	   }'),
       ('ui.main.outlay-calculation.not-selected', '{
		   "1": "Not Selected",
		   "2": "Ընտրված չէ",
		   "3": "Не выбрано"
	   }'),
       ('fields.id', '{
		   "1": "ID",
		   "2": "ID",
		   "3": "ID"
	   }'),
       ('fields.code', '{
		   "1": "Code",
		   "2": "Ծածկագիր",
		   "3": "Код"
	   }'),
       ('fields.title', '{
		   "1": "Title",
		   "2": "Անվանում",
		   "3": "Название"
	   }'),
       ('fields.unit', '{
		   "1": "Unit",
		   "2": "Միավոր",
		   "3": "Единица"
	   }'),
       ('fields.unit-cost', '{
		   "1": "Unit cost",
		   "2": "Միավոր արժեք",
		   "3": "Стоимость единицы"
	   }'),
       ('fields.measure-type', '{
		   "1": "Measure type",
		   "2": "Չափման միավոր",
		   "3": "Тип измирения"
	   }'),
       ('ui.main.outlay-table.structure', '{
		   "1": "Structure",
		   "2": "Կառուցվածք",
		   "3": "Структура"
	   }'),
       ('ui.main.outlay-table.machine/mechanisms', '{
		   "1": "Machine/mechanisms",
		   "2": "Մեքենա/մեխանիզմենր",
		   "3": "Машины/Механизмы"
	   }'),
       ('ui.main.outlay-table.total-cost', '{
		   "1": "Total cost",
		   "2": "Ընդհանուր արժեք",
		   "3": "Общая цена"
	   }'),
       ('ui.main.outlay-table.direct-cost', '{
		   "1": "Direct cost",
		   "2": "Ուղղակի ծախսեր",
		   "3": "Прямые затраты"
	   }'),
       ('ui.main.outlay-table.overhead-cost', '{
		   "1": "Overhead cost",
		   "2": "Վերադիր ծախսեր",
		   "3": "Накладные расходы"
	   }'),
       ('ui.main.outlay-table.net-cost', '{
		   "1": "Net cost",
		   "2": "Ինքնաժեք",
		   "3": "Net cost"
	   }'),
       ('ui.main.outlay-table.profit', '{
		   "1": "Profit",
		   "2": "Շահույթ",
		   "3": "Прибыль"
	   }'),
       ('ui.main.outlay-table.outlay-cost', '{
		   "1": "Total Outlay cost",
		   "2": "Ընդամենը նախահաշվային արժեք",
		   "3": "Общая сметная цена"
	   }'),
       ('measure-types.height', '{
		   "1": "Height",
		   "2": "Բարձրություն",
		   "3": "Высота"
	   }'),
       ('measure-types.perimeter', '{
		   "1": "Perimeter",
		   "2": "Պարագիծ",
		   "3": "Периметр"
	   }'),
       ('measure-types.thickness', '{
		   "1": "Thickness",
		   "2": "Հաստություն",
		   "3": "Толщина"
	   }'),
       ('measure-types.area', '{
		   "1": "Area",
		   "2": "Մակերես",
		   "3": "Площадь"
	   }'),
       ('measure-types.volume', '{
		   "1": "Volume",
		   "2": "Ծավալ",
		   "3": "Объем"
	   }'),
       ('measure-types.weight', '{
		   "1": "Weight",
		   "2": "Քաշ",
		   "3": "Масса"
	   }');


