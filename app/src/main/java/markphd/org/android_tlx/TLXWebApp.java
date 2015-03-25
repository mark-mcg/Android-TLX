package markphd.org.android_tlx;

import android.os.Handler;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

import java.util.ArrayList;

import markphd.org.android_tlx.util.ParticipantDataLog;

/**
 * Javascript interface for the TLX web app.
 * 
 * @author Euan Freeman, Mark McGill
 */
public class TLXWebApp {
	private TLXActivity mParent;
	private ParticipantDataLog mLog;
	private int condition = 0;
	private Handler uiHandler;

	public TLXWebApp(TLXActivity activity, String participantId, String fileDescriptor) {
		Log.v("TLXWebApp", "New web app for " + participantId + " " + fileDescriptor);
	    uiHandler = new Handler();
		mParent = activity;
		condition = Integer.parseInt(fileDescriptor);
		mLog = new ParticipantDataLog(participantId, fileDescriptor);
	}
	
	@JavascriptInterface
    public void alert(final String message) {
        uiHandler.post(    new Runnable() {
            @Override
            public void run() {
                Toast.makeText(mParent, message, Toast.LENGTH_SHORT).show();
            }
        });
    }
    
	@JavascriptInterface
    public void onLoad() {
        uiHandler.post( new Runnable() {
            @Override
            public void run() {
            Toast.makeText(mParent, "Loaded successfully", Toast.LENGTH_SHORT).show();
            ArrayList<Question> Questions = new ArrayList<Question>();


            /*
                I never got round to tidying this up i'm afraid, so here's how it works:

                * you have to hardcode page number and question number for each question
                * if you fuck this up, the question will probably disappear into the ether and the questionnaire will be unusable
                * good luck :P
             */

            // pages i'm using
            int tlxPage = 1;
            int collabPage = 2;
            int susPage = 3;
            int wePage = 4;

            // quesiton sizes i'm using
            int susSize = 5;
            int otherSize = 7;

            // some common scales
            String susLow = "Strongly<br>Disagree";
            String susHigh = "Strongly<br>Agree";


            // shared questions
            // TLX tv viewing
            // question format is: "header", "low scale label", "high scale label", "question", size, page, position/question number
            Questions.add(new Question("Mental Demand - Viewing TV Programme", "Low", "High", "How much mental and perceptual activity was required (e.g. thinking, deciding, calculating, remembering, looking, searching, etc)? Was the task easy or demanding, simple or complex, exacting or forgiving?", 20, tlxPage, 1));
            Questions.add(new Question("Mental Demand - Overall Task", "Low", "High", "See description above.", 20, tlxPage, 2));
//
//            Questions.add(new Question("Physical Demand - Viewing TV Programme", "Low", "High", "How much physical activity was required (e.g. pushing, pulling, turning, controlling, activating, etc)? Was the task easy or demanding, slow or brisk, slack or strenuous, restful or laborious?", 20, tlxPage, 3));
//            Questions.add(new Question("Physical Demand - Overall Task", "Low", "High", "See description above.", 20, tlxPage, 4));
//
//            Questions.add(new Question("Temporal Demand - Viewing TV Programme", "Low", "High", "How much time pressure did you feel due to the rate of pace at which the tasks or task elements occurred? Was the pace slow and leisurely or rapid and frantic?", 20, tlxPage, 5));
//            Questions.add(new Question("Temporal Demand - Overall Task", "Low", "High", "See description above.", 20, tlxPage, 6));
//
//            Questions.add(new Question("Performance - Vieiwng TV Programme", "Low", "High", "How successful do you think you were in accomplishing the goals of the task set by the experimenter (or yourself)? How satisfied were you with your performance in accomplishing these goals?", 20, tlxPage, 7));
//            Questions.add(new Question("Performance - Overall Task", "Low", "High", "See description above.", 20, tlxPage, 8));
//
//            Questions.add(new Question("Effort - Viewing TV Programme", "Low", "High", "How hard did you have to work (mentally and physically) to accomplish your level of performance?", 20, tlxPage, 9));
//            Questions.add(new Question("Effort - Overall Task", "Low", "High", "See description above.", 20, tlxPage, 10));
//
//            Questions.add(new Question("Frustration - Viewing TV Programme", "Low", "High", "How insecure, discouraged, irritated, stressed and annoyed versus secure, gratified, content, relaxed and complacent did you feel during the task?", 20, tlxPage, 11));
//            Questions.add(new Question("Frustration - Overall Task", "Low", "High", "See description above.", 20, tlxPage, 12));
//

            // Collaboration
            // based on websurface
            Questions.add(new Question("WS-1", susLow, susHigh, "We were able to collaborate effectively", otherSize, collabPage, 1));
            Questions.add(new Question("WS-2", susLow, susHigh, "We were able to work independently to complete the task", otherSize, collabPage, 2));
            Questions.add(new Question("WS-3", susLow, susHigh, "It was easy to discuss the information we found", otherSize, collabPage, 3));
            Questions.add(new Question("WS-4", susLow, susHigh, "We were able to work together to complete the task", otherSize, collabPage, 4));
            Questions.add(new Question("WS-5", susLow, susHigh, "I was able to actively participate in completing the task", otherSize, collabPage, 5));

            // based on mobisurf
            Questions.add(new Question("MO-1", "Very<br>Poor", "Very<br>Good", "How well did the system support collaboration?", otherSize, collabPage, 6));
            Questions.add(new Question("MO-2", "Very<br>Poor", "Very<br>Good", "How well did the system support you to share particular information with your partner?", otherSize, collabPage, 7));
            Questions.add(new Question("MO-3", "Strongly<br>disagree", "Strongly<br>Agree", "I was able to tell when my partner was looking at what i was browsing", otherSize, collabPage, 8));
            Questions.add(new Question("MO-4", "Very<br>Poor", "Very<br>Good", "How well did the system support you to see/review what your partner was talking about?", otherSize, collabPage, 9));

            // SUS - related to the mechanisms of screen sharing
            Questions.add(new Question("SUS-1", susLow, susHigh, "I think that I would like to use this type of TV frequently", susSize, susPage, 1));
            Questions.add(new Question("SUS-2", susLow, susHigh, "I found this TV unnecessarily complex", susSize, susPage, 2));
            Questions.add(new Question("SUS-3", susLow, susHigh, "I thought the TV was easy to use", susSize, susPage, 3));
            Questions.add(new Question("SUS-4", susLow, susHigh, "I think i would need the support of a technical person to be able to use this TV", susSize, susPage, 4));
            Questions.add(new Question("SUS-5", susLow, susHigh, "I found the various functions for handling control of the TV fit together well", susSize, susPage, 5));
            Questions.add(new Question("SUS-6", susLow, susHigh, "I thought there was too much inconsistency with this TV", susSize, susPage, 6));
            Questions.add(new Question("SUS-7", susLow, susHigh, "I would imagine that most people would learn to use this TV very quickly", susSize, susPage, 7));
            Questions.add(new Question("SUS-8", susLow, susHigh, "I found the TV very cumbersome to use", susSize, susPage, 8));
            Questions.add(new Question("SUS-9", susLow, susHigh, "I felt very confident using this TV", susSize, susPage, 9));
            Questions.add(new Question("SUS-10", susLow, susHigh, "I needed to learn a lot of things before i could get going with this TV", susSize, susPage, 10));

            // wesearch
            Questions.add(new Question("WE-2", "Strongly<br>Disagree", "Strongly<br>Agree", "I was aware of what my partner was doing", otherSize, wePage, 1));

            // permulin
            Questions.add(new Question("PE-1", "Strongly<br>Disagree", "Strongly<br>Agree", "My partner was aware of what I was doing", otherSize, wePage, 2));
            Questions.add(new Question("DIST-1", "Strongly<br>Disagree", "Strongly<br>Agree", "I found my partners activity distracting", otherSize, wePage, 3));
            Questions.add(new Question("DIST-2", "Strongly<br>Disagree", "Strongly<br>Agree", "I felt I could control how aware I was of my partners activity", otherSize, wePage, 4));

            Questions.add(new Question("ME-1", "Very<br>Poor", "Very<br>Good", "How well did the system support viewing the TV content", otherSize, wePage, 5));
            Questions.add(new Question("ME-2", "Very<br>Poor", "Very<br>Good", "How well did the system support viewing device activity?", otherSize, wePage, 6));

            Questions.add(new Question("ME-3", "Very<br>Unsatisfied", "Very<br>Satisfied", "How satisfied were you with your capability to listen to the TV and the devices being mirrored?", otherSize, wePage, 7));
            Questions.add(new Question("ME-4", "Strongly<br>Disagree", "Strongly<br>Agree", "I found the TV to be distracting", otherSize, wePage, 8));

            // display specific ones
            // usefulness in seeing other peoples content?
            switch(condition){
                case 1:{

                }
                    break;
                case 2:{

                }
                    break;
                case 3:{

                }
                    break;
            }

            for (Question question : Questions){
                mParent.mWebView.loadUrl("javascript:(function() { " +
                        "addQuestion( new Question ('"+question.question+"', '"+question.left+"', '"+question.right+"', '"+question.definition+"', "+question.size+", "+question.page+", "+question.index+") ) " +
                        "})()");
            }
            mParent.mWebView.loadUrl("javascript:( function(){continueLoad();} )() ");
            }
        });
    }
	
    @JavascriptInterface
    public void logSurveyResults(String results){
        //resultString = resultString.substring(1, resultString.length()-1);
        mLog.write(condition + ", " + results);
        Log.v("RESULTS ARE ", results);
        mParent.finishedTLX();

        // if you want to do anything with the results immediately, do it here
        //if (Helper.service != null)
        //	Helper.service.sendResults(results);
    }   
    
	public class Question {
		public String question;
		public String left;
		public String right;
		public String definition;
		public int size;
		public int page;
		public int index;
		
		public Question(String question, String left, String right,
				String definition, int size, int page, int index) {
			super();
			this.question = question;
			this.left = left;
			this.right = right;
			this.definition = definition;
			this.size = size;
			this.page = page;
			this.index = index;
		}
	}
}
