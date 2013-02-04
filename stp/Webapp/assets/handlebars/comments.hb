<script id="hb-commentslist" type="text/x-handlebars-template"> 
<ul>
{{#each json-result}}
	<li>
			<div class="comment-header">
				{{userName}} @ {{modifiedDate}}
			</div>
			<div class="comment">
				{{userComment}}
			</div>	
	</li>	
 {{/each}}
</ul>
</script>	


