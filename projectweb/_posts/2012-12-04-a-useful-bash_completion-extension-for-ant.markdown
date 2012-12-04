---
layout: post
title: A useful bash_completion extension for ant
published: true
tags:
- development 
---
Bash_completion is an utility for bash command interpreter. This interpreter is rather common in linux, but, like the windows counterpards, requires to write too much. Readers may be aware that in latest windows version (in older ones this behavior had to be enabled), when you hit tab, the console tries to find in the same folder which file/folder starts with the same letters as the ones you wrote. In linux, due the console intensive work, we have this tab functionality as well plus bash_completion. Bash_completion is an utility that provides advanced assistance for console addicts. It finds commands that match the current written characters or, if the command is recognised, the most frequent arguments of that command. 

In INGENIAS, we use a lot the [ant](http://ant.apache.org) scripting utility for compiling, running, and other tasks. Each activity is a target in the build.xml, which is an extension of the iaf-generated-build.xml file. The later grows as the system specification grows. It is just exhausting to remember it all. Bash_completion, by default, can parse and advance when you write `ant` in the console, which targets may be invoked next by parsing the build.xml. Nevertheless, it does not parse the files build.xml is extending. 

I dedicate some time to modify the bash_completion file and now this functionality is included. Take the following code, and replace with it the file `/etc/bash_completion.d/ant`. If you don't have it, it may not be available in your linux. If you run a debian based distribution `sudo apt-get install bash_completion` ought to do the trick. If you run windows, I strongly recommend you give a try to [cygwin](http://www.cygwin.com/) and get a taste of linux power in your windows. [Paul in his blog willcode4beer provides with instructions for using bash_completion in windows](http://willcode4beer.com/tips.jspset=tabMaven).

	# bash completion for ant
	have ant &&
	{
	_ant()
	{
	    local cur prev buildfile i
	
	    COMPREPLY=()
	    _get_comp_words_by_ref cur prev
	
	    case $prev in
	        -buildfile|-file|-f)
	            _filedir 'xml'
	            return 0
	            ;;
	        -logfile|-l)
	            _filedir
	            return 0
	            ;;
	        -propertyfile)
	            _filedir properties
	            return 0
	            ;;
	        -nice)
	            COMPREPLY=( $( compgen -W '1 2 3 4 5 6 7 8 9 10' -- "$cur" ) )
	            return 0
	            ;;
	        -lib|-logger|-listener|-D|-inputhandler|-main)
	            return 0
	            ;;
	    esac
	
	    if [[ "$cur" == -* ]]; then
	        COMPREPLY=( $( compgen -W '-help -projecthelp -version -diagnostics \
	            -quiet -verbose -debug -emacs -lib -logfile -logger -listener \
	            -noinput -buildfile -D -keep-going -propertyfile -inputhandler \
	            -find -s -nice -nouserlib -noclasspath -autoproxy -main' \
	            -- "$cur" ) )
	    else
	        # available targets completion
	        # find which buildfile to use
	        buildfile=build.xml
	        for (( i=1; i < COMP_CWORD; i++ )); do
	            if [[ "${COMP_WORDS[i]}" == -@(?(build)file|f) ]]; then
	                buildfile=${COMP_WORDS[i+1]}
	                break
	            fi
	        done
		
	        [ ! -f $buildfile ] && return 0
	
		# identify import tasks and get those files too
	        additionalBuildFiles="$( cat $buildfile | tr "'\t\n>" "\"  \n" | sed -ne 's/.*<import .*file="\([^"]*\).*/\1/p' 2>/dev/null )"
	        
	        # parse buildfile for targets
	        # some versions of sed complain if there's no trailing linefeed,
	        # hence the 2>/dev/null
	        COMPREPLY=( $( compgen -W "$( cat $buildfile $additionalBuildFiles | tr "'\t\n>" "\"  \n" | \
	            sed -ne 's/.*<target .*name="\([^"]*\).*/\1/p' 2>/dev/null )" \
	            -- "$cur" ) )
	        fi
	}
	have complete-ant-cmd.pl && \
	     complete -C complete-ant-cmd.pl -F _ant ant || complete -F _ant ant
	}
	
	# Local variables:
	# mode: shell-script
	# sh-basic-offset: 4
	# sh-indent-comment: t
	# indent-tabs-mode: nil
	# End:
	# ex: ts=4 sw=4 et filetype=sh
	
