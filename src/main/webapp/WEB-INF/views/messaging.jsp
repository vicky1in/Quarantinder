<%@ include file="common/header.jsp" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<script>console.log("${pageContext.request.contextPath}")</script>
<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<title>Chat
	</title>
	<script src="https://cdn.jsdelivr.net/npm/sockjs-client@1/dist/sockjs.min.js"></script>
	<script src="resources/js/stomp.js"></script>

	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
		integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
		crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
		integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
		crossorigin="anonymous"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
		integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
		crossorigin="anonymous"></script>
	<!-- Linking bootstrap style -->
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
		integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
	<style>
		/* https://codepen.io/swards/pen/gxQmbj, regarding the CSS thanks  */
		body {
			font-family: helvetica;
			/* display: flex; */
			flex-direction: column;
			align-items: center;
		}

		.chat {
			width: 100%;
			border: solid 1px #EEE;
			display: flex;
			flex-direction: column;
			padding: 10px;
			overflow: auto;
			max-height: 70vh;
		}

		.messages {
			margin-top: 10px;
			display: flex;
			flex-direction: column;
		}

		.message {
			border-radius: 20px;
			padding: 8px 15px;
			margin-top: 5px;
			margin-bottom: 5px;
			display: inline-block;
		}

		.yours {
			align-items: flex-start;
		}

		.yours .message {
			margin-right: 25%;
			background-color: #eee;
			position: relative;
		}

		.yours .message.last:before {
			content: "";
			position: absolute;
			z-index: 0;
			bottom: 0;
			left: -7px;
			height: 20px;
			width: 20px;
			background: #eee;
			border-bottom-right-radius: 15px;
		}

		.yours .message.last:after {
			content: "";
			position: absolute;
			z-index: 1;
			bottom: 0;
			left: -10px;
			width: 10px;
			height: 20px;
			background: white;
			border-bottom-right-radius: 10px;
		}

		.mine {
			align-items: flex-end;
		}

		.mine .message {
			color: white;
			margin-left: 25%;
			background: linear-gradient(to bottom, #00D0EA 0%, #0085D1 100%);
			background-attachment: fixed;
			position: relative;
		}

		.mine .message.last:before {
			content: "";
			position: absolute;
			z-index: 0;
			bottom: 0;
			right: -8px;
			height: 20px;
			width: 20px;
			background: linear-gradient(to bottom, #00D0EA 0%, #0085D1 100%);
			background-attachment: fixed;
			border-bottom-left-radius: 15px;
		}

		.mine .message.last:after {
			content: "";
			position: absolute;
			z-index: 1;
			bottom: 0;
			right: -10px;
			width: 10px;
			height: 20px;
			background: white;
			border-bottom-left-radius: 10px;
		}
	</style>
	<!-- Need to download Stomp and include it above -->
	<script type="text/javascript">
		var stompClient = null;

		function setConnected(connected) {
			// document.getElementById('connect').disabled = connected;
			// document.getElementById('disconnect').disabled = !connected;
			// document.getElementById('conversationDiv').style.visibility
			// 	= connected ? 'visible' : 'hidden';
			// document.getElementById('response').innerHTML = '';
		}

		function connect() {
			var socket = new SockJS('/chat');
			stompClient = Stomp.over(socket);
			stompClient.connect({}, function (frame) {
				setConnected(true);
				console.log('Connected: ' + frame);
				stompClient.subscribe('/topic/messages', function (messageOutput) {
					console.log("message output")
					console.log(messageOutput)
					showMessageOutput(JSON.parse(messageOutput.body));
				});
			});

			// load the previous messages

		}

		// function loadPreviousMessages(){
		// 	idTo = document.getElementById('to').value;
		// 	idFrom = document.getElementById('from').value;
		// 	stompClient.send("/app/getAllChat", {}, 
		// 		JSON.stringify({'from':idFrom, 'to': idTo, 'message': "initial Load"}));

		// }

		function disconnect() {
			if (stompClient != null) {
				stompClient.disconnect();
			}
			setConnected(false);
			console.log("Disconnected");
		}

		function sendMessage() {
			var from = "${from}";
			var to = "${to}"
			var text = document.getElementById('text').value;
			// Only sends actual messages
			if (text != "") {
				stompClient.send("/app/chat", {},
					JSON.stringify({ 'from': from, 'to': to, 'message': text }));
				clearMessageForm()
			}

		}

		function clearMessageForm() {
			var text = document.getElementById('text');
			text.value = '';
		}

		// function emojify(text){
		// 	const supportedEmojis = {
		// 		":p":  String.fromCodePoint(parseInt("128540"))
		// 	}


		// 	return text


		// }

		function showMessageOutput(messageOutput) {
			// Commented out to stop the message tab showing up
			// var response = document.getElementById('response');
			// var p = document.createElement('p');
			// p.style.wordWrap = 'break-word';
			// p.appendChild(document.createTextNode(messageOutput.from + ": " 
			//   + messageOutput.message ));
			// response.appendChild(p);

			// Check the identity of whos sending the message and show accordingly
			var myId = "${from}"
			var communicatingToId = "${to}"
			var recipientId = messageOutput.to;
			// NOTE:
			// Check if the message is being sent to you, if not them it wont be shown. It is also added to the database
			console.log("Sending to: " + recipientId)

			// Correct communications channel
			if ((myId === messageOutput.to && communicatingToId === messageOutput.from) || (myId === messageOutput.from && communicatingToId === messageOutput.to)) {

				// Whether its to or from you in the communications channel
				if (myId === messageOutput.from) {
					showMyMessage(messageOutput)
				} else {
					showYourMessage(messageOutput)
				}
				var chatwin = document.getElementById("chatWindow");
				chatwin.scrollTop = chatwin.scrollHeight;
			}

		}

		function startUpLoadingPreviousMessages(messageList) {
			console.log()
			var length = "${messages.size()}"
			for (i = 0; i < length; i++) {
				console.log("${messages.size()}")
			}

		}

		function showMyMessage(messageOutput) {
			var chatwindow = document.getElementById('chatWindow');
			var myMessage = document.createElement('div')
			myMessage.setAttribute('class', 'mine messages');
			var myMessageText = document.createElement('span');
			myMessageText.setAttribute('class', 'message');
			myMessageText.setAttribute('title', new Date().toLocaleString());

			myMessageText.textContent = messageOutput.message;
			myMessage.appendChild(myMessageText);
			chatwindow.appendChild(myMessage);
		}

		function showYourMessage(messageOutput) {
			var chatwindow = document.getElementById('chatWindow');
			var myMessage = document.createElement('div')
			myMessage.setAttribute('class', 'yours messages');
			var myMessageText = document.createElement('span');
			myMessageText.setAttribute('class', 'message');

			myMessageText.setAttribute('title', new Date().toLocaleString());

			// newmessage = emojify(messageOutput.message)

			myMessageText.textContent = messageOutput.message;
			myMessage.appendChild(myMessageText);
			chatwindow.appendChild(myMessage);
		}

		function showMyStartupMessage(messageOutput, messageTime) {
			var chatwindow = document.getElementById('chatWindow');
			var myMessage = document.createElement('div')
			myMessage.setAttribute('class', 'mine messages');
			var myMessageText = document.createElement('span');
			myMessageText.setAttribute('class', 'message');
			myMessageText.setAttribute('title', new Date(messageTime).toLocaleString());
			myMessageText.textContent = messageOutput;
			myMessage.appendChild(myMessageText);


			chatwindow.appendChild(myMessage);
		}

		function showYourStartupMessage(messageOutput, messageTime) {
			var chatwindow = document.getElementById('chatWindow');
			var myMessage = document.createElement('div')
			myMessage.setAttribute('class', 'yours messages');
			var myMessageText = document.createElement('span');
			myMessageText.setAttribute('class', 'message');
			myMessageText.setAttribute('title', new Date(messageTime).toLocaleString());
			// newmessage = emojify(messageOutput.message)

			myMessageText.textContent = messageOutput;
			myMessage.appendChild(myMessageText);



			chatwindow.appendChild(myMessage);
		}

		async function startupConnection() {
			await disconnect();
			connect();
		}



	</script>
</head>

<body onload="startupConnection()">
	<div class="container">

		<h2>Chatting to ${toName}!</h1>

			<div id=chatWindow class="chat">
			</div>
			<div id=sendButton class="chat">
				<div class="input-group">
					<input type="text" class="form-control" id="text" placeholder="Write a message...">
					<span class="input-group-btn">
						<button class="btn btn-default" type="button" onclick="sendMessage();">Send!</button>
					</span>
				</div>

			</div>


			<div>
				<c:if test="${messages.size() > 0}">
					<c:forEach begin="0" end="${fn:length(messages) - 1}" var="index">

						<script>
							var sen = "${from}"
							var rec = "${to}"
							console.log(rec)
							console.log(sen)
							console.log("${messages[index].getFrom()}")
							console.log("${messages[index].getTo()}")

							console.log("${messages[index].getMessage()}")
							console.log("${messages[index].getDateTimeSent()}")
							if ("${messages[index].getFrom()}" === sen) {
								showMyStartupMessage("${messages[index].getMessage()}", "${messages[index].getDateTimeSent()}")
							} else if ("${messages[index].getTo()}" === sen) {
								showYourStartupMessage("${messages[index].getMessage()}", "${messages[index].getDateTimeSent()}")
							}
							var chatwin = document.getElementById("chatWindow");
							chatwin.scrollTop = chatwin.scrollHeight;
						</script>



					</c:forEach>

					<script>console.log("${principal.getName()}")</script>
				</c:if>
			</div>

	</div>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
	<script>
		$("#text").on('keyup', function (event) {
			if (event.keyCode === 13) {
				sendMessage();
			}
		});
	</script>
</body>

</html>
<%@ include file = "common/footer.jsp" %>