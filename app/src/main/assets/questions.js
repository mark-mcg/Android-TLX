var pages = new Array();
lastPage = 1;

function getPage(i){
	if (pages[i] == null)
		pages[i] = new Array();

	if (i > lastPage)
		lastPage = i;		
	return pages[i]
}

function getLastPage(){
	return lastPage;
}

function Question(scale, left, right, def, size, page, index){

	this.scale = scale;
	this.left = left;
	this.right = right;
	this.def = def;
	this.page = page;
	this.size = size;
	this.index = index;
	this.result = -1;
}

function addQuestion(question){
	getPage(question.page)[question.index] = question;
}

function setResult(pageIndex, questionIndex, result){
	getPage(pageIndex)[questionIndex].result = result;
}

function checkResultsForPage(pageIndex){
	questions = getPage(pageIndex);
	
	for (i = 1; i != questions.length; i++){
	
		if ( questions[i] != null && questions[i].result == -1){
			window.alert("Question " + i + " hasn't been completed");
			return false;
		}
	}
	
	return true;
}

function getResults(){
	results = "";
	for (i =1; i != pages.length; i++){
		questions = pages[i];

		for (j = 1; j != questions.length; j++){
			window.alert("question " + j + " page " + i);
			results += questions[j].result;
			
			if (! (i == pages.length-1 && j == questions.length-1) )
				results += ", ";
		}
	}
	
	return results;
}


function setupTestQuestions(){
	addQuestion(new Question("Frustration", "Strongly Disagree", "Strongly Agree", "How insecure, discouraged, irritated, stressed and annoyed versus secure, gratified, content, relaxed and complacent did you feel during the task?", 7, 1, 1));

	//addQuestion(new Question("Mental Demand", "Low", "High", "How much mental and perceptual activity was required (e.g. thinking, deciding, calculating, remembering, looking, searching, etc)? Was the task easy or demanding, simple or complex, exacting or forgiving?", 20, 1, 1));
	addQuestion(new Question("Physical Demand", "Low", "High", "How much physical activity was required (e.g. pushing, pulling, turning, controlling, activating, etc)? Was the task easy or demanding, slow or brisk, slack or strenuous, restful or laborious?", 20, 1, 2));
	addQuestion(new Question("Temporal Demand", "Low", "High", "How much time pressure did you feel due to the rate of pace at which the tasks or task elements occurred? Was the pace slow and leisurely or rapid and frantic?", 20, 1, 3));
	addQuestion(new Question("Performance", "Low", "High", "How successful do you think you were in accomplishing the goals of the task set by the experimenter (or yourself)? How satisfied were you with your performance in accomplishing these goals?", 20, 1, 4));
	addQuestion(new Question("Effort", "Low", "High", "How hard did you have to work (mentally and physically) to accomplish your level of performance?", 20, 1, 5));
	addQuestion(new Question("Frustration", "Strongly Disagree", "Strongly Agree", "How insecure, discouraged, irritated, stressed and annoyed versus secure, gratified, content, relaxed and complacent did you feel during the task?", 7, 1, 6));
	
	addQuestion(new Question("lol", "lolol", "lol", "lol", 3, 2, 1));
	addQuestion(new Question("lol", "lolol", "lol", "lol", 3, 2, 2));
	addQuestion(new Question("lol", "lolol", "lol", "lol", 3, 2, 3));
	addQuestion(new Question("lol", "lolol", "lol", "lol", 3, 2, 1));
	addQuestion(new Question("lol", "lolol", "lol", "lol", 3, 3, 1));
	addQuestion(new Question("lol", "lolol", "lol", "lol", 3, 3, 2));
	
}

function testQuestions(){
	
	setupTestQuestions();
	setResult(1, 1, -1);
	checkResultsForPage(1);
	checkResultsForPage(2);
	checkResultsForPage(3);
	
	window.alert(getResults());
}
