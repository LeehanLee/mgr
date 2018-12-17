webpackJsonp([4],{"+z5O":function(e,t,n){"use strict";Object.defineProperty(t,"__esModule",{value:!0});var r=n("lk5p"),i={created:function(){window.ListService.get(this.listUrl,{vm:this}).then(this.handleListDataReceived)},data:function(){return{formLabelWidth:"200px",dtoFormVisible:!1,currentDto:{enabled:!1},rules:{dtoname:[{required:!0,message:"请输入组织名",trigger:"change"},{min:2,max:16,message:"长度在 2 到 16 个字符",trigger:"change"}]},currentIndex:-1,message:"",treeData:[]}},methods:{handleSubmitClick:function(e,t){var n=this,i=this;this.$refs.ruleForm.validate(function(e){if(!e)return n.$message({message:"请处理完错误后再保存",type:"error"}),!1;i.currentDto.id?window.ActionService.post("/api/org/update",n.currentDto,{successMsg:"保存成功",errorMsg:"保存失败"}).then(function(e){var t=Object(r.c)(i.treeData,i.currentDto.id);t.enabled=i.currentDto.enabled,t.label=i.currentDto.name,i.dtoFormVisible=!1}):window.ActionService.post("/api/org/insert",i.currentDto,{successMsg:"添加成功",errorMsg:"添加失败"}).then(function(e){i.dtoFormVisible=!1,window.ListService.get(i.listUrl,{vm:n}).then(i.handleListDataReceived)})})},handleListDataReceived:function(e){this.treeData=Object(r.b)(e.rows),this._.isEmpty(e.rows)&&(this.message="No data yet.")},showAddingForm:function(e){this.dtoFormVisible=!0,this.currentDto={parentid:e?e.value:null,parentname:e?e.label:"无"}},handleEditClick:function(e,t){this.dtoFormVisible=!0,this.currentDto={parentid:e.parent?e.parent.data.value:null,parentname:e.parent?e.parent.data.label:"无",name:t.label,id:t.value,enabled:t.enabled}},handleRemoveClick:function(e,t){var n="/api/org/delete?id="+t.value;window.HandleDeleteClick(this,n)},handleToggleEnableClick:function(e){var t=this.$refs.orgTree.getCheckedNodes().map(function(e){return e.value});if(this._.isEmpty(t))return this.$message({message:"请先勾选要"+(e?"启":"禁")+"用的组织",type:"error"}),!1;var n=t.map(function(e){return'"'+e+'"'}).join(","),r="/api/org/"+(e?"enable":"disable");window.ToggleEnableAndAlert(this,r,n,null,e)},getPageButtonFunc:function(e){switch(e){case"org/insert":return this.showAddingForm();case"org/enable":return this.handleToggleEnableClick(!0);case"org/disable":return this.handleToggleEnableClick(!1)}},getItemButtonFunc:function(e){var t=this;switch(e){case"org/update":return function(e){t.currentDto=t._.assign({},e),t.dtoFormVisible=!0,t.currentIndex=t._.findIndex(t.listData,function(n){return t._.isEqual(n,e)})};case"org/enable":return this.getToggleEnableAndAlertFunc(t,!0);case"org/disable":return this.getToggleEnableAndAlertFunc(t,!1);case"org/delete":return function(e){var n="/api/org/delete?id="+e.value;window.HandleDeleteClick(t,n)}}},getToggleEnableAndAlertFunc:function(e,t){return function(n){var r="/api/org/"+(t?"enable":"disable");window.ToggleEnableAndAlert(e,r,n.value,null,t)}},showItemBtn:function(e,t){return!(e.id.indexOf("delete")>=0)||(!t.children||t.children.length<=0)}},computed:{listUrl:function(){return"/api/org/getList?page=1&pageSize=999999"},modelTitle:function(){return this.currentDto.id?"编辑组织":"添加组织"},pageRights:function(){return Object(r.e)(this.$store,"org")},itemRights:function(){var e=this;return Object(r.d)(this.$store,"org").map(function(t){return{text:t.name,id:t.id,handleClick:e.getItemButtonFunc(t.id)}})},containerHeight:function(){return{height:this._.isEmpty(this.pageRights)?"calc(100% - 180px)":"calc(100% - 236px)"}}}},a={render:function(){var e=this,t=e.$createElement,n=e._self._c||t;return n("div",{staticClass:"main-content"},[n("el-breadcrumb",{attrs:{separator:"/"}},[n("el-breadcrumb-item",{staticClass:"other"},[e._v("组织管理")]),e._v(" "),n("el-breadcrumb-item",[e._v("组织列表")])],1),e._v(" "),n("el-row",e._l(e.pageRights,function(t,r){return n("el-button",{key:r,attrs:{type:"primary",size:"small"},on:{click:function(n){e.getPageButtonFunc(t.id)}}},[e._v(e._s(t.name))])}),1),e._v(" "),n("div",{class:{"tree-container":!0,"org-tree":!0,"msg-container":!!e.message},style:e.containerHeight},[e.message?n("div",{staticClass:"msg"},[e._v(e._s(e.message))]):n("div",{staticClass:"tree-area"},[n("el-tree",{ref:"orgTree",attrs:{data:e.treeData,"show-checkbox":"","node-key":"id","check-strictly":!1,"default-expand-all":"","expand-on-click-node":!1},scopedSlots:e._u([{key:"default",fn:function(t){t.node;var r=t.data;return n("span",{staticClass:"custom-tree-node"},[n("span",{class:r.enabled?"item-enabled":"item-disabled"},[e._v(e._s(r.label+(r.enabled?"":"(已禁用)")))]),e._v(" "),n("span",e._l(e.itemRights,function(t){return e.showItemBtn(t,r)?n("el-button",{key:t.id,class:{"delete-btn":t.id.indexOf("delete")>=0},attrs:{type:"text",size:"mini"},on:{click:function(e){t.handleClick(r)}}},[e._v(e._s(t.text))]):e._e()}),1)])}}])})],1)]),e._v(" "),n("el-dialog",{attrs:{modal:!1,title:e.modelTitle,visible:e.dtoFormVisible},on:{"update:visible":function(t){e.dtoFormVisible=t}}},[n("el-form",{ref:"ruleForm",attrs:{model:e.currentDto,rules:e.rules}},[e.currentDto.parentid?n("el-form-item",{attrs:{label:"上级组织","label-width":e.formLabelWidth,prop:"name"}},[n("span",[e._v(e._s(e.currentDto.parentname))])]):e._e(),e._v(" "),n("el-form-item",{attrs:{label:"组织名","label-width":e.formLabelWidth,prop:"name"}},[n("el-input",{attrs:{autocomplete:"off"},model:{value:e.currentDto.name,callback:function(t){e.$set(e.currentDto,"name",t)},expression:"currentDto.name"}})],1),e._v(" "),n("el-form-item",{attrs:{label:"","label-width":e.formLabelWidth,prop:"enabled"}},[n("el-checkbox",{model:{value:e.currentDto.enabled,callback:function(t){e.$set(e.currentDto,"enabled",t)},expression:"currentDto.enabled"}},[e._v("启用")])],1)],1),e._v(" "),n("div",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[n("el-button",{attrs:{type:"primary"},on:{click:e.handleSubmitClick}},[e._v("确定")]),e._v(" "),n("el-button",{on:{click:function(t){e.dtoFormVisible=!1}}},[e._v("取消")])],1)],1)],1)},staticRenderFns:[]};var l=n("VU/8")(i,a,!1,function(e){n("vsnV")},null,null);t.default=l.exports},vsnV:function(e,t){}});