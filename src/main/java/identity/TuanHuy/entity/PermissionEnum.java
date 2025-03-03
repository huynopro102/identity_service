package identity.TuanHuy.entity;

public enum PermissionEnum {
    // Post Permissions
    CREATE_POST,
    EDIT_POST,
    DELETE_POST,
    PUBLISH_POST,
    EDIT_ANY_POST,
    DELETE_ANY_POST,
    SCHEDULE_POST,
    DRAFT_POST,
    RESTORE_POST,

    // Comment Permissions
    COMMENT,
    DELETE_COMMENT,
    DELETE_ANY_COMMENT,
    EDIT_COMMENT,
    MODERATE_COMMENTS,

    // User Management
    MANAGE_USERS,
    BAN_USER,
    UNBAN_USER,
    EDIT_USER_ROLE,
    VIEW_USER_PROFILE,

    // Admin & Settings
    MANAGE_CATEGORIES,
    MANAGE_TAGS,
    MANAGE_SETTINGS,
    ACCESS_ADMIN_PANEL,
    MANAGE_PLUGINS,
    BACKUP_DATA,
    RESTORE_DATA,
    MANAGE_THEMES,

    // Statistics & Reports
    VIEW_STATS,
    VIEW_INDIVIDUAL_POST_STATS,
    EXPORT_REPORTS,
    VIEW_TRAFFIC_SOURCES,

    // Media Management
    UPLOAD_MEDIA,
    DELETE_MEDIA,
    DELETE_ANY_MEDIA,
    MANAGE_MEDIA_LIBRARY
}
