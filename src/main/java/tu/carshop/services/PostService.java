package tu.carshop.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tu.carshop.dtos.PostDTO;
import tu.carshop.exceptions.ObjectNotFoundException;
import tu.carshop.mapper.PostMapper;
import tu.carshop.models.Offer;
import tu.carshop.models.Post;
import tu.carshop.models.User;
import tu.carshop.repositories.OfferRepository;
import tu.carshop.repositories.PostRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService extends BaseService<PostDTO> {
  private static final String POST_NOT_FOUND_ERROR_MESSAGE = "Post with id %s not found!";

  private final PostRepository postRepository;
  private final PostMapper postMapper;
  private final UserService userService;
  private final OfferRepository offerRepository; // Dummy way, but don't have time rn

  @Override
  public List<PostDTO> getAll() {
    return postRepository.findAll()
        .stream()
        .map(postMapper::toDTO)
        .toList();
  }

  @Override
  public PostDTO findById(Long id) {
    return postRepository.findById(id)
        .map(postMapper::toDTO)
        .orElseThrow(() -> new ObjectNotFoundException(String.format(POST_NOT_FOUND_ERROR_MESSAGE, id)));
  }

  @Override
  public boolean deleteById(Long id) {
    postRepository.deleteById(id);
    return true;
  }

  @Override
  public PostDTO update(Long id, PostDTO updateDto) {
    Post post = postRepository.findById(id)
        .orElseThrow(() -> new ObjectNotFoundException(String.format(POST_NOT_FOUND_ERROR_MESSAGE, id)));

    post.setTitle(updateDto.getTitle());
    post.setContent(updateDto.getContent());
    post.setModified(LocalDateTime.now());

    return postMapper.toDTO(postRepository.save(post));
  }

  public Long addPost(PostDTO postDto, String username) {
    User postAuthor = userService.findByUsername(username);
    Offer offer = offerRepository.findById(postDto.getOfferId()).orElseThrow();

    Post post = postMapper.toEntity(postDto);
    post.setAuthor(postAuthor);
    post.setModified(LocalDateTime.now());
    post.setCreated(LocalDateTime.now());
    post.setOffer(offer);

    return postRepository.save(post).getId();
  }

  public List<PostDTO> getAllPostsForOffer(Long id) {
    return postRepository.findAllByOfferId(id).stream()
        .map(postMapper::toDTO)
        .toList();
  }
}
