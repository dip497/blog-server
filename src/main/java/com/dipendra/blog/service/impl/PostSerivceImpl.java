package com.dipendra.blog.service.impl;

import com.dipendra.blog.entity.Post;
import com.dipendra.blog.exception.ResourceNotFoundException;
import com.dipendra.blog.payload.PostDto;
import com.dipendra.blog.payload.PostResponse;
import com.dipendra.blog.repository.PostRepository;
import com.dipendra.blog.service.PostService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostSerivceImpl implements PostService {
    private final PostRepository postRepository;
    public PostSerivceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public PostDto createPost(PostDto postDto) {
        Post newPost = postRepository.save(mapToEntity(postDto));
        return mapToDTO(newPost);
    }

    @Override
    public PostResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<Post> posts = postRepository.findAll(pageable);
        List<Post> listofPosts = posts.getContent();

        List<PostDto> content = listofPosts.stream().map(this::mapToDTO).toList();
        PostResponse postResponse = new PostResponse();
        postResponse.setContent(content);
        postResponse.setPageNo(posts.getNumber());
        postResponse.setPageSize(posts.getSize());
        postResponse.setTotalElements(posts.getTotalElements());
        postResponse.setTotatPages(posts.getTotalPages());
        postResponse.setLast(posts.isLast());
        return postResponse;
    }

    @Override
    public PostDto getPostById(long id) {
        return mapToDTO(postRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Post","id",id)));
    }

    @Override
    public PostDto updatePostById(PostDto postDto,long id) {
        Post post = postRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Post","id",id));
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());
        return mapToDTO(postRepository.save(post));
    }

    @Override
    public void deletePostById(long id) {

        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
        postRepository.delete(post);
    }

    private PostDto mapToDTO(Post post){
        return new PostDto(post.getId(), post.getTitle(),post.getDescription(), post.getContent());
    }
    private Post mapToEntity(PostDto postDto){
       return new Post(postDto.getTitle(),postDto.getDescription(),postDto.getContent());
    }
}
