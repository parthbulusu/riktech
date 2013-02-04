<script id="hb-technologies" type="text/x-handlebars-template"> 
	<li class="kb-tile selected" id="{{uid}}">
		<ul>
		{{#each json-result}}
			<li>
				{{#if question}}
					<div class="question">
						{{question}}
					</div>
					{{#each answerChoices}}
						<div class="answer">
							<span style="display:none" id="hilit_element">div</span>
							<span style="display:none" id="t_id">0</span>
							<span style="display:none" id="q_id">{{nextQuestId}}</span>
							<a href="javascript:return false;">
								{{ansChoice}}
							</a>	
						</div>	
					{{/each}}	
				{{else}}
					<div class="technology">
						<span style="display:none" id="hilit_element">li</span>
						<span style="display:none" id="t_id">{{id}}</span>
						<span style="display:none" id="q_id">{{questionId}}</span>
						<a href="javascript:return false;">
							{{name}}
						</a>
					</div>
				 {{/if}}
			</li>	
		 {{/each}}
		</ul>
	</li>
</script>	


