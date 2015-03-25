# Android-TLX

Can be used for generic Likert scale questions of any Likert scale size. Questions are logged to SD card.

##How to start questionnaire:
See MainActivity.java for examples on how to invoke questionnaire directly, or invoke a settings page (e.g. name, condition) first.

##How to modify questionnaire:
See question examples in TLXWebApp.

##History
Original version was hacked together from Keith Vertanens HTML TLX app ( http://www.keithv.com/software/nasatlx/ )

Euan Freeman then added some logging to file (because I like to live dangerously) and tidied up the UI. ( http://euanfreeman.co.uk/projects/tlx-for-android/ )

Because I suck at version control, I kept developing my version, adding in the ability to generically define questions/pages, automatic scrolling between questions, variable length Likert scale fields. Essentially developing this far more than it ever should have been (should really have abandoned the current webview approach long ago). But it's a useful tool for offline Likert scale questionnaires at any rate!
