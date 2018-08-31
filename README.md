# URL Extractors
Programs written in both Python and Java that can be used to extract and find URLs inside of a given website.

## Summary
This program requests that the user enter a valid URL. Provided that the user's input is a well-formed URL, the program will then begin reading from the website's html code until the input stream is empty. Using regular expressions, the data read from the URL is searched for embedded links, ignoring duplicates. All links that are found are then printed to the console in alphabetical order for the user to peruse. 
