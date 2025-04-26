import { useEffect, useState } from "react";
import { usePostStore } from "../../store/postStore";
import { PostType } from "../../types/post.type";
import { Posts } from "../../components/Posts";
import { useNavigate, useParams } from "react-router-dom";
import { useUsersStore } from "../../store/usersStore";

export const UserProfilePage = () => {
  const navigate = useNavigate();

  const { username } = useParams<{ username: string }>();
  const [posts, setPosts] = useState<PostType[]>([]);
  const { getPostsByUserName, likePost, bookmarkPost } = usePostStore();
  const { user } = useUsersStore();

  useEffect(() => {
    const fetchDatas = async () => {
      const result = await getPostsByUserName(username || "", 1);
      setPosts(result);
    };

    window.scrollTo(0, 0);

    fetchDatas();
  }, []);

  useEffect(() => {
    if (user.username === username) navigate("/profile");
  }, []);

  const handleLike = async (post: PostType) => {
    await likePost(post._id);
    const data = await getPostsByUserName(username || "", 1);
    setPosts(data);
  };

  const handleBookmark = async (post: PostType) => {
    await bookmarkPost(post._id);
    const data = await getPostsByUserName(username || "", 1);
    setPosts(data);
  };

  return (
    <Posts
      posts={posts}
      handleLike={handleLike}
      handleBookmark={handleBookmark}
    />
  );
};
