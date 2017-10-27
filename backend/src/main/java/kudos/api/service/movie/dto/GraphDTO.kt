package kudos.api.service.movie.dto

import kudos.domain.model.persistent.ogm.entities.Movie

data class GraphDTO(val nodes: List<NodeDTO>,
                    val links: List<LinkDTO>) {

    companion object {
        fun mapFromEntity(movies : Collection<Movie>) : GraphDTO
        {
            val nodes = java.util.ArrayList<NodeDTO>()
            val links = java.util.ArrayList<LinkDTO>()
            var i = 0
            val result = movies.iterator()
            while (result.hasNext()) {
                val movie = result.next()
                nodes.add(NodeDTO(title = movie.title, label = "movie"))
                val target = i
                i++
                for (role in movie.roles) {
                    val actor = NodeDTO(title = role.person.name, label = "actor")
                    var source = nodes.indexOf(actor)
                    if (source == -1) {
                        nodes.add(actor)
                        source = i++
                    }
                    links.add(LinkDTO(source, target))
                }
            }
            return GraphDTO(nodes, links)
        }
    }

}