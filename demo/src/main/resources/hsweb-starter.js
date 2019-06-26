/*
 * Copyright 2016 http://www.hswebframework.org
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */
//组件信息
var info = {
    groupId: "${project.groupId}",
    artifactId: "${project.artifactId}",
    version: "${project.version}",
    website: "https://github.com/hs-web/",
    author: "admin@hsweb.me",
    comment: "演示系统"
};
var menus = [{
    "describe": " ",
    "icon": "fa fa-cogs",
    "id": "e9dc96d6b677cbae865670e6813f5e8b",
    "name": "总览",
    "parentId": "-1",
    "path": "sOrB",
    "permissionId": "",
    "sortIndex": 1,
    "status": 1,
    "url": ""
}, {
    "describe": " ",
    "icon": "fa fa-sitemap",
    "id": "org-01",
    "name": "主机",
    "parentId": "-1",
    "path": "a2o0",
    "permissionId": "",
    "sortIndex": 2,
    "status": 1,
    "url": ""
}, {
    "describe": " ",
    "icon": "fa fa-th-list",
    "id": "dev-01",
    "name": "个人钱包",
    "parentId": "-1",
    "path": "d010",
    "permissionId": "",
    "sortIndex": 3,
    "status": 1,
    "url": ""
}, {
    "icon": "fa fa-deaf",
    "id": "b7f349813e3e67348c78f069f92ced46",
    "name": "个人中心",
    "parentId": "-1",
    "path": "0I2J",
    "sortIndex": 4,
    "status": 1
}}];


var user = [
    {
        "id": "4291d7da9005377ec9aec4a71ea837f",
        "name": "超级管理员",
        "username": "admin",
        "password": "ba7a97be0609c22fa1d300691dfcd790",
        "salt": "HX8Hr5Yd",
        "status": 1,
        "creatorId": "admin",
        "createTime": new Date().getTime()
    }
];

var autz_setting = [
    {
        "id": "98d74130b3cb06afc0ae8e5b57a6c052",
        "type": "user",
        "settingFor": "4291d7da9005377ec9aec4a71ea837f",
        "describe": null,
        "status": 1
    }
];
var autz_menu = [];
menus.forEach(function (menu) {
    autz_menu.push({
        id: org.hswebframework.web.id.IDGenerator.MD5.generate(),
        parentId: "-1",
        menuId: menu.id,
        status: 1,
        settingId: "98d74130b3cb06afc0ae8e5b57a6c052",
        path: "-"
    });
});
//版本更新信息
var versions = [
    // {
    //     version: "3.0.0",
    //     upgrade: function (context) {
    //         java.lang.System.out.println("更新到3.0.2了");
    //     }
    // }
];
var JDBCType = java.sql.JDBCType;

function install(context) {
    var database = context.database;

}

function initialize(context) {
    var database = context.database;
    database.getTable("s_menu").createInsert().values(menus).exec();
    database.getTable("s_autz_setting").createInsert().values(autz_setting).exec();
    database.getTable("s_autz_menu").createInsert().values(autz_menu).exec();
    database.getTable("s_user").createInsert().values(user).exec();
}

//设置依赖
dependency.setup(info)
    .onInstall(install)
    .onUpgrade(function (context) { //更新时执行
        var upgrader = context.upgrader;
        upgrader.filter(versions)
            .upgrade(function (newVer) {
                newVer.upgrade(context);
            });
    })
    .onUninstall(function (context) { //卸载时执行

    }).onInitialize(initialize);