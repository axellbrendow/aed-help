#!/bin/bash
# http://www.grymoire.com/Unix/Sed.html#uh-35a

extrairCampo() {
	campo="$1"

	# Declaração de variáveis
	procurar_intervalo="/$campo/,/<\/tr/p"
	pular_campo="/$campo/n"
	remover_tudo_antes_do_campo="s/.*$campo/$campo@/"
	colocar_delimitador_entre_o_campo="s/$campo/\|$campo\|/g"

	# Extração do campo e seu valor
	sed -ne "$cortar_tabela" < "$arquivo" | \
		sed -ne "$procurar_intervalo" -ne "$pular_campo" \
			-ne "$sair_no_fim_do_intervalo" | \
		tr "\n" "@" | sed "$colocar_delimitador_entre_o_campo" | cut -d "|" -f 2,3 | \
		sed -ne "$remover_entidades" -ne "$remover_tudo_antes_do_campo" \
			-ne "$remover_tudo_depois_da_tag" -ne "$remover_tags" -ne "s/|//pg"
}

if [[ "$1" = "--help" || "$1" = "-h" ]]; then
	echo '
USAGE: [PATH_TO_HTML_FILE] FIELDS_TO_EXTRACT...

Example: ./htmlget.sh Juventus_F.C..html "Full name" "Nickname(s)" "Short name" "Founded" "Ground" "Capacity" "Owner" "Chairman" "Head coach"'

else
	# Extração dos argumentos
	arquivo="$1"
	shift
	campo="$1"

	cortar_tabela="/<table.*infobox/,/<\/table/p"
	sair_no_fim_do_intervalo="/(<\/tr)|(<tr)/q"
	remover_entidades="s/&[^;]*;/ /g"
	remover_tudo_depois_da_tag="s/<\/tr.*//g"
	remover_tags="s/<[^>]*>//g"

	while test -n "$campo"  # -n = nonzero length
	do
		extrairCampo "$campo"
		shift
		campo="$1"
	done
fi
