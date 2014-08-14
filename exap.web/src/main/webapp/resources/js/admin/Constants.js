var CONSTANTS = {
        EXAM_TIMEOUT : 1200, // 20 minutes
        MENU: {
            COLLAPSED: true,
            QUESTION: {
                COLLAPSED: false
            },
            EXAM: {
                COLLAPSED: false
            }
        },
        EVENTS: {
            OVERVIEW_ENTITY_NAME_SET: 'exap.overview.entity.name.set',
            CRUD_READ_ENTITIES: 'exap.crud.read.entities',
            CRUD_CONTEXT_MENU_REMOVE_ENTITY: 'exap.crud.context.menu.remove.entity',
            EXAM_TIMEOUT: 'exap.exam.timeout'
        },
        CRUD_OPERATIONS: {
            CREATE: 'CREATE',
            READ: 'READ',
            UPDATE: 'UPDATE',
            DELETE: 'DELETE'
        },
        CODEMIRROR_LANGUAGES: [
            // 'text/x-csrc', 'text/x-c++src', 'text/x-java', 'text/x-csharp'
            {name: "Java", code: "text/x-java"},
            {name: "C", code: "text/x-csrc"},
            {name: "C++", code: "text/x-c++src"},
            {name: "C#", code: "text/x-csharp"}
        ],
        QUESTION_TYPE: {
            OPEN: "OPEN",
            CLOSED: "CLOSED-SINGLE",
            CLOSED_MUTLI: "CLOSED-MULTI"
        },
        EXAM_TYPE: {
            OPEN: "OPEN",
            CLOSED: "CLOSED"
        }
    }
    ;
var EVENTS = CONSTANTS.EVENTS;
var CRUD = CONSTANTS.CRUD_OPERATIONS;