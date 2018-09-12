# Shared Custom classes

alias st='open -a SourceTree `pwd`'
alias pj='cd ~/Projects'
alias cl='cd ~/Projects/CargoLedger'
alias es='cd ~/Projects/ESchouten'
alias pullall='sh ~/Projects/ESchouten/CustomClasses/Pull.sh'
alias ls='ls -G'
alias ll='ls -la'
alias ..='cd ..'

function gp() {
    git add .
    git commit -a -m "$1"
    git push
}
