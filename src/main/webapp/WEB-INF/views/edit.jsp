<!DOCTYPE html>
<%@ include file="common/include.jsp"%>
<html>
	<head>
		<meta charset="utf-8" />
		<title></title>
		<script src="https://cdn.jsdelivr.net/npm/vue" type="text/javascript" charset="utf-8"></script>
		
		<style>
		  .m-content {
		    text-align: center;
		  }
		  .m-content {
		      max-width: 960px;
		      margin: 0 auto;
		      text-align: center;
		    }
		    .maction {
		      margin: 10px 0;
		    }
		</style>
		<scrpit src="./mavon-editor/dist/mavon-editor.js"></scrpit>
<%--		<link  rel="stylesheet"  "./mavon-editor/dist/css/index.css" />--%>
		<script src="https://unpkg.com/element-ui/lib/index.js"></script>
		<link  rel="stylesheet"  href="https://unpkg.com/element-ui/lib/theme-chalk/index.css" />
	</head>
	<%@ include file = "common/header.jsp" %>
	<body>
		<div id="edit">
			<div id="userid" style="display: none">${user.id}</div>
		<div class="m-content">
		    <div class="block">
<%--		      <div>{{ user.username }}</div>--%>
		    </div>
		
		    <div class="maction">
		      <span><el-link href="./posts">Previous Posts</el-link></span>
		      <el-divider direction="vertical"></el-divider>
		      <span><el-link type="success" href="./create">Create</el-link></span>
		
		      <el-divider direction="vertical"></el-divider>
		      <!-- <span v-show="!hasLogin"><el-link type="primary" href="/login">Login</el-link></span> -->
		
		      <!-- <span v-show="hasLogin"><el-link type="danger" @click="logout">Exit</el-link></span> -->
		    </div>
		
		  </div>
		  <div>		  
		      <div class="m-content">
		  
		        <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
		  
		          <el-form-item label="CONTENT" prop="postBody">
		            <!-- <mavon-editor v-model="ruleForm.postBody"></mavon-editor>-->
					<el-input type="textarea" v-model="ruleForm.postBody" :rows="10"/>
		          </el-form-item>
		  
		          <el-form-item>
		            <el-button type="primary" @click="submitForm('ruleForm')">CREATE</el-button>
		            <el-button @click="resetForm('ruleForm')">RESET</el-button>
		          </el-form-item>
		        </el-form>
		  
		      </div>
		  
		    </div>
			</div>
	</body>
	<%@ include file = "common/footer.jsp" %>
	<script src="http://cdn.suoluomei.com/common/js2.0/axios/axios.min.js"></script>
	<script>
	// Vue.use(mavonEditor)
	const baseUrl = 'http://localhost:8080'
	
	 new Vue({
	 	el: '#edit',
	 	data() {
	      return {
	      	userid: ${user.id},
			 user: {
					   username: 'USER',
					   avatar: 'https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png'
					 },
			 hasLogin: false,
	        ruleForm: {
	          postId: 0,
	          userId: ${user.id},
	          timePosted: '',
	          postBody: ''
	        },
	        rules: {
	          postBody: [
	            { trequired: true, message: 'null', trigger: 'blur' }
	          ]
	        }
	      };
	    },
	    methods: {
	      submitForm(formName) {
	        this.ruleForm.timePosted = "0000-00-00 00:00:00"
	        this.$refs[formName].validate((valid) => {
	          if (valid) {
	            const _this = this
	            axios.post(baseUrl + '/api/posts', this.ruleForm).then(res => {
	              console.log(res)
	              _this.$alert('SUCCESS', '', {
	                confirmButtonText: 'OK',
	                callback: action => {
	                  window.location.href=("./posts")
	                }
	              });
	
	            })
	
	          } else {
	            console.log('error submit!!');
	            return false;
	          }
	        });
	      },
	      resetForm(formName) {
	        this.$refs[formName].resetFields();
	      }
	    },
	    created() {
	 		console.log(${user.id})
	    }
	  })
	</script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</html>
