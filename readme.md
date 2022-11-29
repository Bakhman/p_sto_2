# Задание: Реализовать тесты в MessageResourceController
*******************************************************************************************
Описание:
- addMessageStar
- deleteStarMessageById

Необходимо написать для для всех этих методов интеграционный тест, важно:

1. один кейс один тестовый метод
2. нужно разобраться что делает каждый метод, если не понятно что-то спрашивать тимлида
3. писать тесты согласно структуре
********************************************************************************************
## Документация JMStack

### Работа c git

#### Клонирование проекта

1. На странице репозитория убедитесь, что выбрана ветка **dev** (1), нажмите кнопку **Clone** (2), скопируйте ссылку (3)
   .

![](src/main/resources/static/images/git_tutor/git_clone_url.png)

2. Откройте **Intellij IDEA**, нажмите **Get from version control** на экране приветствия, либо **VCS | Git | Clone...**
   в меню.

![](src/main/resources/static/images/git_tutor/git_clone_get.png)

![](src/main/resources/static/images/git_tutor/git_clone_get_alt.png)

3. Вставьте скопированную ссылку в строку **URL**, нажмите **Clone**.

![](src/main/resources/static/images/git_tutor/git_clone_clone.png)

### Перед внесением изменений в код

Создайте новую ветку в git-репозитории и работайте в ней. Для этого:

1. Нажмите на текущую ветку **dev** в правом нижнем углу.

![](src/main/resources/static/images/git_tutor/git_branch.png)

2. Выберите **New branch**.

![](src/main/resources/static/images/git_tutor/git_branch_create.png)

3. Введите название своей новой ветки (на ваше усмотрение) и нажмите **Create**.

![](src/main/resources/static/images/git_tutor/git_branch_name.png)

### Добавление своего кода в общий репозиторий. Git push.

Прежде чем создать merge request вам необходимо подготовить вашу ветку к отправке в общий репозиторий.

1. Нажмите на текущую ветку в правом нижнем углу. Выберите опцию **dev | update**. Таким образом вы скачаете в свою
   локальную ветку **dev** все коммиты которые были замержены, пока вы работали в своей ветке.

![](src/main/resources/static/images/git_tutor/git_premerge_update_dev.png)

2. Убедитесь, что в данный момент активна ваша рабочая ветка (значек ярлыка слева от имени, как у ветки my-branch на
   скриншоте). Выберите опцию **dev | Merge into Current**. Таким образом вы добавите все изменения из ветки **dev** в
   вашу ветку. При возникновении конфликтов разрешите их.

![](src/main/resources/static/images/git_tutor/git_premerge_merge_dev.png)

3. ---**ВАЖНО**--- Убедитесь что проект собирается и запускается.

4. Выберите вашу ветку и нажмите на **Push...**, чтобы добавить её в общий репозиторий.

![](src/main/resources/static/images/git_tutor/git_premerge_push.png)

### Создание merge request

1. Создайте новый merge request. В качестве **Source branch** выберите свою ветку, **Target branch** - **dev**.

![](src/main/resources/static/images/git_tutor/git_merge_req.png)

![](src/main/resources/static/images/git_tutor/git_merge_req_new.png)

![](src/main/resources/static/images/git_tutor/git_merge_req_src_trg.png)

2. Проверьте данные, допишите комментарии при необходимости. Обратите внимание на опцию **Delete source branch when
   merge request is accepted**. Завершите создание реквеста, приложите ссылку на него в карточку таска на Trello.

![](src/main/resources/static/images/git_tutor/git_merge_req_final.png)

## Сущности

### User

#### Поля:

- **id** - уникальный идентификационный номер пользователя;
- **fullName** - полное имя пользователя;
- **password** - пароль;
- **persistDateTime** - дата регистрации;
- **role** - ссылка на объект роли данного пользователя;
- **lastUpdateDateTime** - дата последней авторизации;
- **email** - адрес электронной почты;
- **about** - краткая информация о пользователе;
- **city** - город пользователя;
- **linkSite** - ссылка на сайт;
- **linkGitHub** - ссылка на github;
- **linkVk** - ссылка на страницу во Вконтакте;
- **isEnabled** - отметка, что учетная запись не заблокирована;
- **image** - ссылка на фото пользователя;

```
Пользователь может задавать вопросы, отвечать на вопросы и давать комментарии к вопросам и ответам.
Наделен ролью.Может помечать понравившиеся вопросы, отмечать вопросы которые были полезны. Заданный
вопрос может отметить, как решенный, указав на ответ, который помог решить проблему.
```

### Role

#### Поля:

- **id** - уникальный идентификационный номер роли;
- **name** - имя роли;

```
Определяет порядок прав действий пользователя в системе.
```

### Question

#### Поля:

- **id** - уникальный идентификационный номер вопроса;
- **title** - заголовок вопроса;
- **description** - описание вопроса;
- **persistDateTime** - дата публикации вопроса;
- **user** - пользователь - объект, опубликовавший вопрос;
- **tags** - теги, которыми обозначен вопрос;
- **lastUpdateDateTime** - дата последней редакции вопроса или добавления ответа;
- **isDeleted** - флаг, помечающий объект, как удалённый. Отображаться при запросе данный вопрос не будет;

```
Сущность, которую инициализирует пользователь для публикации своего вопроса. Имеет заголовок, который кратко 
описывает суть вопроса, развернутое описание, с возможностью вставки фрагмента кода. Может быть помечен полями
“решен”, “любимый вопрос”. Отметка “решен” проставляется автором вопроса, с указанием ответа, который помог
решить возникший вопрос. Отметка “любимый вопрос” ставиться любым пользователем, который посчитал вопрос
актуальным и интересным. ”Тэг” проставляется автором вопроса, для классификации вопроса. Под вопросом может
также быть оставлен комментарий любым пользователем, включая автора вопроса.
```

### VoteQuestion

#### Поля

- **id** - уникальный идентификационный номер;
- **user** - пользователь, который отправил свой голос;
- **question** - вопрос, по которому ведётся голосование;
- **localDateTime** - дата и время отправки голоса;
- **vote** - значение голоса, который отправил пользователь по вопросу;

```
Таблица, которая содержит в себе записи голосования пользователей по вопросам. В таблице используется
сборный внешний ключ, который состоит из полей user, qustion, localDateTime. Для создания необходимо
передать сущности User, Question и значения голоса. Допускается передача значений только "1" и "-1".
Пользователь может проголосовать за один вопрос только с отклонением в 1 пункт. Допускается, что пользователь
может отменить свой голос, отправив противоположное значение предыдущего голоса. Или изменить свой итоговый
голос, при этом отправив повторно обратное значение. Все действия пользователя сохраняются в таблице.
Итоговое значение "полезности вопроса" является сумма всех голосов.
```

### Answer

#### Поля:

- **id** - уникальный идентификационный номер ответа;
- **user** - пользователь, который опубликовал ответ;
- **question** - вопрос, к которому относится ответ;
- **htmlBody** - тело ответа;
- **persistDateTime** - дата публикации ответа;
- **updateDateTime** - дата публикации ответа;
- **isHelpful** - отметка, что именно этот ответ помог решить вопрос, к которому оно относиться. Эту отметку может
  ставить только автор вопроса;
- **dateAcceptTime** - дата, решения вопроса;
- **isDeleted** - флаг, помечающий объект, как удалённый. Отображаться при запросе данный ответ не будет;
- **isDeletedByModerator** - флаг, помечающий объект удаленный модератором

```
Сущность, которую инициализирует пользователь отвечая на вопрос. Привязан к сущности question. Ответ на
вопрос может оставлять любой пользователь. Может быть предложено несколько вариантов ответов на опубликованный
вопрос. Ответ может быть помечен автором вопроса, к которому был оставлен ответ, как “решение помогло”,
обозначая тем самым, что сам вопрос решён и помог прямо или косвенно данный ответ. Под ответом пользователи
могут оставлять комментарии, которые уточняют или дополняют предложенное решение. Каждый пользователь может
оставлять под вопросом только один ответ.
```

### VoteAnswer

#### Поля

- **id** - уникальный идентификационный номер;
- **user** - пользователь, который отправил свой голос;
- **answer** - ответ, по которому ведётся голосование;
- **persistDateTime** - дата и время отправки голоса;
- **vote** - значение голоса, который отправил пользователь по ответу;

```
Таблица, которая содержит в себе записи голосования пользователей по ответам. В таблице используется
сборный внешний ключ, который состоит из полей user, answer, persistDateTime. Для создания необходимо
передать сущности User, Answer и значения голоса. Допускается передача значений только "1" и "-1".
Пользователь может проголосовать за один вопрос только с отклонением в 1 пункт. Не допускается, что пользователь
может отменить свой голос. Все действия пользователя сохраняются в таблице.
```

### Comment

#### Поля:

- **id** - уникальный идентификационный номер комментария;
- **user** - пользователь, который оставил комментарий;
- **text** - содержание комментария;
- **persistDateTime** - дата публикации комментария;
- **lastUpdateDateTime** - дата последней редакции;
- **commentType** - тип комментария. Идентифицирует родительскую сущность, к которой был оставлен комментарий
  (вопрос или ответ);

```
Комментарий оставляется пользователем под любым вопросом или ответом, для уточнения или дополнения к основному
посту.
```

### User_favorite_question (UserFavoriteQuestion.java)

#### Поля:

- **id** - уникальный идентификационный номер записи об отмеченном вопросе;
- **persistDateTime** - дата постановки отметки “понравившейся вопрос”;
- **user** - пользователь, который отметил вопрос, как понравившийся;
- **question** - вопрос, который пользователь отметил, как понравившейся;

```
Отметка понравившегося вопроса проставляется пользователем, который счел вопрос интересным и/или полезным.
```

### Tag

#### Поля:

- **id** - уникальный идентификационный номер тега;
- **name** - название тега;
- **description** - описание тега;
- **persistDateTime** - дата создания тега;
- **questions** - список вопросов, которыми помечен данный тег;

```
Ставиться у сущности question для классификации вопроса.
```

### Related_tags

#### Поля:

- **id** - уникальный номер записи;
- **childTag** - дочерний тег;
- **mainTag** - родительский тег;

```
Категоризация тэгов. Показывает взаимосвязь общего с конкретным запросом. Например тэг “База данных” будет
иметь более широкую область запросов, в то время как тэг “Hibernate” будет более предметную область, которая
одновременно подходит под широкое использование тэга “База данных”.
```

### Tag_has_question

#### Поля

- **tag_id** - идентификационный номер тега;
- **question_id** - идентификационный номер вопроса;
- **persist_date** - дата отметки вопроса тегом;

```                                                  
Производная таблица связи many-to-many сущности вопросов и тегов.
```

### Editor

#### Поля:

- **id** - уникальный идентификационный номер редактора;
- **count** - правки сделанные за день
- **persist_date** - дата
- **user_id** - идентификационный номер пользователя;

```
Сущность, которая хранит в себе историю редактирования вопроса, 
ответа или комментария сделанных пользователями.
```

### Moderator

#### Поля:

- **id** - уникальный идентификационный номер модератора;
- **persist_date** - дата назначения;
- **user_id** - идентификационный номер пользователя;

```
Сущность, которая хранит пользователей чей статус являеться модератором. 
Привилегия, выдаваемая системой в зависимости от уровня репутации участника.
```

### Reputation

#### Поля

- **id** - уникальный идентификационный номер репутации
- **count** - баллы
- **persist_date** - дата
- **author** - пользователь, которому ставят оценки и о чье репутации идет речь
- **sender** - пользователь, который ставит оценку, оценивающий
- **answer** - ответ, если баллы начисленны за ответ
- **question** - вопрос, если баллы начисленны за вопрос
- **type** - тип

```
Сущность, которая хранит в себе информацию об оценке одного пользователя другим за какое либо-действие.
Нипример, за заданный вопрос.  
```

### Badges

#### Поля

- **id** - уникальный идентификационный номер знака
- **badges** - имя знака
- **count** - минимальное количество очков репутации для получения знака
- **description** - описание знака

```
Сущность знаков.   
```

### user_badges

#### Поля

- **id** - уникальный идентификационный номер знака
- **ready** - имеет булевский тип, если помечается true знак получен
- **badges_id** - идентификационный номер знака
- **user_id** - идентификационный номер пользователя

```
Промежуточная сущность связывающая таблицы User и Badges.
User при регистрации получает все знаки лишь поле ready определяет заслужил пользователь знак или нет.
```

### Tag_ignored (IgnoredTag.java)

#### Поля

- **id** - уникальный идентификационный номер знака
- **user** - ссылка на профиль пользователя
- **ignoredTag** - ссылка на тег
- **persistDateTime** - дата добавления тега в справочник

```
Справочник тегов которые пользователь добавляет в игнорируемые
```

### Tag_tracked (TrackedTag.java)

#### Поля

- **id** - уникальный идентификационный номер знака
- **user** - пользователь
- **trackedTag** - тег
- **persistDateTime** - дата добавления тега в справочник

```
Справочник тегов которые пользователь добавляет в отслеживаемые 
```

### Bookmarks

#### Поля

- **id** - уникальный идентификационный номер закладки
- **user** - пользователь
- **question** - вопрос

```
Таблица закладок
```

[Схема](https://dbdiagram.io/d/6086b027b29a09603d12295d)

### Документация по использованию Пагинации на фронте.

#### Работа функций (файл user.js)

1. Получение токена из cookie.
2. Обращение к необходимой нам api (в нашем случае - получение всех юзеров отсортированных по дате регистрации)
3. Определение асинхронной функции с передачей в нее api- мы сохраняем ответ полученный из нашего запроса и передаем в Заголовок наш токен. Далее сохраняем наш ответ.
4. Парсим в таблицу куда будем передавать необходимые нам items
5. Указываем как будем обращаться к нашей таблице в файле Html

![](src/main/resources/static/images/paginationFront/pagination1.png)

![](src/main/resources/static/images/paginationFront/pagination2.png)


#### Работа с Html.
1. Вызов нашей таблицы в Html
2. Импорт наших файлов js в Html
3. Отрисовка кнопок "Предыдущей" и "Следующей"

![](src/main/resources/static/images/paginationFront/htmlImport.png)



## Как пользоваться конвертором MapStruct.

**MapStruct** - это генератор кода, который значительно упрощает реализацию сопоставлений между типами Java-компонентов
на основе подхода соглашения по конфигурации. Сгенерированный код сопоставления использует простые вызовы методов и,
следовательно, является быстрым, безопасным по типам и простым для понимания. Более подробно можно ознакомиться в
официальной документации:https://mapstruct.org/ .

В текущем проекте **Developer Social** технология **MapStruct** используется,в основном, для преобразования **Dto** в **
Entity** и наоборот. Названия всех классов преобразования должны заканчиваться на «**Converter**» (например: **
GroupChatConverter**) и должны храниться в пакете **converters**. Такой класс должен быть абстрактным, помеченным
аннотацией **@Mapper**.Эта аннотация отмечает класс как класс сопоставления и позволяет процессору **MapStruct**
включиться во время компиляции. Методы должны быть абстрактными,из их названия должно быть явно понятно,какой класс во
что преобразуется. Например: (**chatDtoToGroupChat**- преобразует **ChatDto** в **GroupChat**).

Если соответствующие поля двух классов имеют разные названия, для их сопоставления используется аннотация **@Mapping**.
Эта аннотация ставится над абстрактным методом преобразования и имеет следующие обязательные поля:

**source** - исходное поле преобразовываемого класса.
**target**- конечное поле класса,в котором должно быть значение из **source**.

Для разрешения неоднозначностей в именах полей классов можно указывать их с именем соответствующего параметра метода
преобразования. например:(**source** = "**chatDto.title**", где **chatDto** - имя параметра метода преобразования)

По умолчанию, метод должен принимать объект преобразовываемого класса, но также можно передавать различные другие
параметры(например **Id**) и потставлять их в **source**, чтобы в дальнейшем поле **target** приняло это значение.

Могут возникнуть ситуации,что нужно преобразовать поле в другой тип данных,например в коллекцию и наоборот.Тогда в
аннотацию **@Mapping** следует добавить еще одно поле:
**qualifiedByName**, которое будет содержать имя метода, реализующего логику получения нужного типа или значения. В
таком случае этот метод должен быть помечен аннотацией
**@Named** c указанием названия для маппинга. Ниже приведён общий пример:

````
{@Mapping(source = "chatDto.title", target = "title")
    @Mapping(source = "chatDto.image", target = "image")
    @Mapping(source = "userId",target ="users",qualifiedByName = "userIdToSet")
    public abstract GroupChat chatDtoToGroupChat(ChatDto chatDto,Long userId); }"
   

@Named("userIdToSet")
    public  Set<User> userIdToSet(Long userId) {
        User user = userService.getById(userId);
        Set<User> userSet = new HashSet<>();
        userSet.add(user);
        return userSet;
    }
````

## Использование Swagger
![](src/main/resources/static/images/swagger/swagger_logo.png)

**Swagger** - это фреймворк для спецификации RESTful API, дает возможность интерактивно просматривать спецификацию,
и отправлять запросы (Swagger UI).

**Swagger UI** - интерфейс, который представляет документацию, позволяет возможность просмотреть какие типы запросов
есть, описание моделей и их типов данных.
URL для Swagger UI: http://localhost:8091/swagger-ui.html

**Swagger Editor** - онлайн-редактор, позволяет писать документацию в YAML или JSON формата. (https://editor.swagger.io/)
URL для Swagger Editor: http://localhost:8091/v2/api-docs

**Swagger** имеет два подхода к написанию документации:
- Документация пишется на основании вашего кода: достаточно добавить несколько зависимостей в проект и  
добавить конфигурацию. Код проекта становиться не очень читабельным от обилия аннотаций и описания в них.
  Вся документация будет вписана в нашем коде (все контроллеры и модели превращаются в некий Java Swagger Code)
- Документация пишется отдельно от кода: Данный подход требует знать синтаксис Swagger Specification.
  Документация пишется либо в YAML/JSON файле, либо в редакторе Swagger Editor.

**Swagger UI**
**Swagger UI** - позволяет визуализировать ресурсы API и взаимодействовать с ними без какой-либо логики реализации. 
Он автоматически генерируется из вашей спецификации OpenAPI (ранее известной как Swagger), 
а визуальная документация упрощает внутреннюю реализацию и использование на стороне клиента.

Вот пример Swagger UI который визуализирует документацию для моего pet-project:
![](src/main/resources/static/images/swagger/111.png)
![](src/main/resources/static/images/swagger/222.png)
мы можем выполнить запрос за сервер и получить ответ от него:
![](src/main/resources/static/images/swagger/333.png)


# Настройка подключения к базе данных

1. Run->Edit Configurations...
   ![](src/main/resources/static/images/edit_configurations/edit_configurations.png)
2. В появившемся окне выбираем Configuration->Environment->Environment variable:
   ![](src/main/resources/static/images/edit_configurations/environment.png)
3. Вводим свои значения для ключей db_url_protocol; db_url_hosts; db_url_port; db_url_dbName; 
db_user_name; db_password; server_port
   ![](src/main/resources/static/images/edit_configurations/environment_variable.png)

# Выбор профиля для запуска проекта

1. Run->Edit Configurations...
   ![](src/main/resources/static/images/edit_configurations/edit_configurations.png)
2. В появившемся окне выбираем Configuration->Spring Boot->Active profiles:
3. Вводим пользователя dev или local
   ![](src/main/resources/static/images/edit_configurations/active_profile.png)

## Тестирование

### Структура тестов

## Правила написания тестов
1. Все классы для тестов находиться в папке **.../api**.
2. Тесты создаются согласно рест контроллерам. Например, если есть `ResourseAnswerController`, то есть тест `TestResourseAnswerController` и тестируются все api из контроллера.
3. Все тестовые классы должны наследоваться от абстрактного класса в котором описана все конфигурация тестов.
4. В пакете **test/resource/script** лежат скрипты для инициализации данных перед тестом и после его
5. Все сущности описанные в sql скриптах для загрузки тестовых данных начинаются с `id = 100`.
6. **НЕЛЬЗЯ ИЗМЕНЯТЬ УЖЕ НАПИСАННЫЕ СКРИПТЫ, ТОЛЬКО ЕСЛИ НЕ ОБАНУРЖЕН БАГ!**,
7. На каждый метод тестового класса написаны отдельные sql скрипты
8. Нельзя использовать аннотацию **@Transactional** для тестов.
9. структура написание скриптов следующая: script/[пакет с названием тестового контроллера]/[пакет с названием тестового метода] и далее в нем 2 скрипта Before.sql и After.sql, в Before данные, которые вы хотите, чтоб были когда запускался тест, в After удаление всех данных после выполнение тестого метода

## Миграция базы данных при помощи FlyWay

В application.properties в данный момент используется свойство ddl-auto=validate,
что позволяет при каждом запуске приложения проверять соответствие наших Entity с таблицами
в базе данных. FlyWay позволяет нам переложить ответственность на создание структуры базы данных с
Hibernate на него. При запуске FlyWay читает скрипты из "resources/db/migration" и пытается заполнить схему исходя
из этого скрипта. Если это первый запуск, то так же создаться таблица "FlyWay_schema_history" в которой будут храниться
все изменения базы данных, и происходит запись данных в схему согласно скрипту, далее Hibernate сверяет наши Entity на
соответствие таблиц в схеме и наших Entity, если всё хорошо, то программа продолжит работу.
При повторном запуске FlyWay проверяет необходимо ли обновить базу данных, сверяя хэш из скрипта с тем что хранится
в таблице "FlyWay_schema_history", если обновление не требуется то этап миграции пропускается, далее Hibernate снова
сверяет данные, и если всё хорошо, то программа продолжает работу.
Если нам требуется изменить структуру базы данных, то нам необходимо создать новый скрипт который должен называться
"V{версия базы данных}__{описание}.sql" и в нём создать новую схему базы данных.
**ВНОСИТЬ ИЗМЕНЕНИЯ В СУЩЕСТВУЮЩИЕ СКРИПТЫ КАТЕГОРИЧЕСКИ НЕЛЬЗЯ! Для каждой версии базы данных необходимо создать новый!**

## JWT токен или продвинутая авторизация пользователей

JWT token позволяет контролировать доступ пользователя к разным ресурсам нашего сервиса. Кому-то администратор может
выдать "бан", просто убрав токен из хедеров страницы. А послушные и адекватные пользователи будут иметь доступ к разным
ресурсам, лишь периодически обновляя его. Как же пользоваться данным токеном? Сначала давайте посмотрим на пример, когда
пользователь с почтой 123@mail.ru попытается зайти на наш сервис. При попытке перехода на любую страницу, несущую
какую-то информацию, пользователь получит статус 403 - Forbidden, поскольку у него еще нет активного токена.

![](src/main/resources/static/images/jwt/forbidden403.jpg)

Для того, чтобы сгенерировать токен, необходимо перейти по ссылке, которая находится внизу сообщения об ошибке, либо же
самостоятельно перейти на api/auth/token. Контроллеру будут переданы данные о пользователе, и он на основе почты
сгенерирует JWT токен. ![](src/main/resources/static/images/jwt/clickLink.jpg)

При переходе по ссылке контроллер вернет новый сгенерированный токен и отправит его. Его нужно скопировать.
![](src/main/resources/static/images/jwt/JWTtoken.jpg)

ТЕПЕРЬ САМЫЙ ВАЖНЫЙ ШАГ!
Заходим в Headers, в поле Key вводим Authorization, а в Value сначала пишем "Bearer " 
(после Bearer поставить пробел), после чего вставляем наш токен в то же поле. И обязательно поставить галочку
слева от Authorization, чтобы при запросе этот хедер учитывался.
![](src/main/resources/static/images/jwt/bearer.jpg)

Готово! Теперь, при условии, что хедер Authorization будет активным, в течении 24 часов (время можно изменить)
пользователь сможет пользоваться сервисом и свободно переходить по ссылкам в зависимости от его роли.
![](src/main/resources/static/images/jwt/done.jpg)




