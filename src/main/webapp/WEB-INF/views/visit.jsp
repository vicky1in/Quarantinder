<!DOCTYPE html>
<%@ include file="common/include.jsp"%>
<html>
	<head>
		<meta charset="utf-8" />
		<title></title>
		<script src="https://cdn.jsdelivr.net/npm/vue" type="text/javascript" charset="utf-8"></script>
		
		<style>
		
		  .mpage {
		    margin: 0 auto;
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
		<link  rel="stylesheet"  href="https://unpkg.com/element-ui/lib/theme-chalk/index.css" />
		
		<script src="https://unpkg.com/element-ui/lib/index.js"></script>
	</head>
	<%@ include file = "common/header.jsp" %>

	<body>
		<div id="blog">
			
		
		<div class="m-content">
		    <h3>WELCOME TO</h3>
		    <div class="block">
<%--		      <el-avatar :size="50" :src="user.avatar"></el-avatar>--%>
		      <div>${visitedUser.name}'s blog</div>
		    </div>
		
		    <div class="maction">
<%--		      <span><el-link type="success" href="./posts">Previous Posts</el-link></span>--%>
<%--		      <el-divider direction="vertical"></el-divider>--%>
<%--&lt;%&ndash;		      <span><el-link  href="./create">Create</el-link></span>&ndash;%&gt;--%>
<%--		--%>
<%--		      <el-divider direction="vertical"></el-divider>--%>
		      <!-- <span v-show="!hasLogin"><el-link type="primary" href="/login">Login</el-link></span> -->
		
		      <!-- <span v-show="hasLogin"><el-link type="danger" @click="logout">Exit</el-link></span> -->
		    </div>
		
		  </div>
			<div id="userid" style="display: none">${user.id}</div>
		  <div class="mcontaner">
		      <div class="block" style="max-width: 960px; margin: auto">
		        <el-timeline>
		  
		          <el-timeline-item :timestamp="blog.created" placement="top" v-for="blog in blogs">
		            <el-card>
		              <h4>
		                <!-- <router-link :to="{name: 'BlogDetail', params: {blogId: blog.id}}">
		                  {{blog.title}}
		                </router-link> -->
		                {{blog.postBody}}
		              </h4>
		              <div style="width:60%; display: inline-block">{{blog.timePosted}}</div>
		              <div style="float: right; display: inline-block">
		                <i class="el-icon-chat-round" @click="openDialog(blog.postId)"></i>
<%--		                <i style="margin-left: 20px" class="el-icon-delete" @click="deletePost(blog.postId)"></i>--%>
		                <i style="margin-left: 20px" class="el-icon-more" @click="display(blog.postId)"></i>
		              </div>
		            </el-card>
		            <div v-if="show_id === blog.postId">
						<el-timeline>
							<el-timeline-item :timestamp="dateFormat('mm-dd-YYYY HH:MM:SS', new Date(comment.timePosted)) + '     By ' + comment.name" placement="bottom" v-for="comment in comments">
								<el-card>

									<h3>
										{{comment.commentBody}}
									</h3>
								</el-card>

							</el-timeline-item>
						</el-timeline>
		            </div>
		          </el-timeline-item>
		  
		        </el-timeline>
		  
		        <el-dialog
		          title="Comment"
		          :visible.sync="commentVs"
		          width="40%"
		          :before-close="dialogBeforeClose">
		          <div>
		            <el-input type="textarea" v-model="commentForm.commentBody" placeholder=""></el-input>
		          </div>
		          <div slot="footer">
		            <el-button @click="commentVs = false">cancel</el-button>
		            <el-button type="primary" @click="submit()">submit</el-button>
		          </div>
		        </el-dialog>
		  
		        <!-- <el-pagination class="mpage"
		                       background
		                       layout="prev, pager, next"
		                       :current-page="currentPage"
		                       :page-size="pageSize"
		                       :total="total"
		                       @current-change=page>
		        </el-pagination> -->
		  
		      </div>
		  
		    </div>
			</div>
	</body>
	<%@ include file = "common/footer.jsp" %>
	<script src="http://cdn.suoluomei.com/common/js2.0/axios/axios.min.js"></script>
	<script>
		function dateFormat(fmt, date) {
			let ret;
			const opt = {
				"Y+": date.getFullYear().toString(),       
				"m+": (date.getMonth() + 1).toString(),    
				"d+": date.getDate().toString(),            
				"H+": date.getHours().toString(),           
				"M+": date.getMinutes().toString(),        
				"S+": date.getSeconds().toString()          
			};
			for (let k in opt) {
				ret = new RegExp("(" + k + ")").exec(fmt);
				if (ret) {
					fmt = fmt.replace(ret[1], (ret[1].length == 1) ? (opt[k]) : (opt[k].padStart(ret[1].length, "0")))
				};
			};
			return fmt;
		}
		const baseUrl = 'http://localhost:8080'
		new Vue({
			el: '#blog',
			data() {
			  return {
			  	visitedUserId: ${visitedUser.id},
			  	userid: ${user.id},
				show_id: 0,
				blogs:{},
				commentForm:  {commentBody:'', postId: 1, timePosted: '2020-10-30 12:30:11', userId: ${user.id}},
				commentVs: false,
				comments:[],
				user: {
				          username: 'USER',
				          avatar: 'https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png'
				        },
				hasLogin: false
			  }
			},
	    methods: {
	      display(id){
	        if(id === this.show_id){
	          this.show_id = 0
	          return
	        }
	
	        this.show_id = id
	        
	        axios.get('/api/comments?postId=' + id)
	        .then(
	          res =>{
	            this.comments = res.data
	            console.log(this.comments);
	          }
	        )
	      },
	      openDialog(id){
	      	this.commentForm.postId = id
	        this.commentVs = true
	      },
	      dialogBeforeClose(){
	      
	        this.commentForm.commentBody = ''
	        this.commentForm.postId = 0
	        this.commentForm.timePosted = '2020-10-30 12:30:11'
	        this.commentForm.userId = this.userid
	        this.commentVs = false
	      },
	      submit(){
	        var data = JSON.parse(JSON.stringify(this.commentForm))
	        axios.post(baseUrl + "/api/comments", data)
	        .then(
	          res =>{
					this.$message({
					message: "comment success",
					type: 'success'
					});
	              this.dialogBeforeClose()
			  if (this.show_id == data.postId){
				  axios.get('/api/comments?postId=' + data.postId)
						  .then(
								  res =>{
					  this.comments = res.data
					  console.log(this.comments);
			  }
			  )
			  }
	          }
	        )


	      },

	      page(currentPage) {
	        const _this = this
	        axios.get(baseUrl + "/api/posts?userId=" + this.visitedUserId).then(res => {
	          // console.log('res', res)
	          this.blogs = res.data
	          // console.log(this.blogs);
	        })
	      }
	    },
	    created() {
			// this.userid = document.getElementById("userid").innerText
	      	this.page(1)
	    }
	  })
	</script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</html>
